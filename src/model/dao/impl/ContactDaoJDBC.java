package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ContactDao;
import model.entities.Contact;
import model.entities.ContactList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDaoJDBC implements ContactDao {

    private Connection conn;

    public ContactDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Contact obj) {

        PreparedStatement st = null;

        try {
            if (obj.getContactList() == null || obj.getContactList().getId() == null) {
                throw new IllegalArgumentException("Insert error: ContactList or ContactList ID is null!");
            }

            st = conn.prepareStatement(
                    "INSERT INTO contact " +
                            "(Name, Email, PhoneNumber, GroupId) " +
                            "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getPhoneNumber());
            st.setInt(4, obj.getContactList().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Contact obj) {

        if (obj.getId() == null) {
            throw new IllegalArgumentException("Update error: Contact ID is null!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE contact " +
                            "SET Name = ?, Email = ?, phoneNumber = ? " +
                            "WHERE id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getPhoneNumber());
            st.setInt(4, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM contact WHERE id = ? ");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Contact findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT contact.*, contactlist.groupName as GroupName, contactlist.id as GroupId "
                            + "FROM contact "
                            + "INNER JOIN contactlist "
                            + "ON contact.GroupId = contactlist.id "
                            + "WHERE contact.id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                ContactList contactList = instantiateContactList(rs);
                Contact obj = instantiateContact(rs, contactList);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Contact instantiateContact(ResultSet rs, ContactList contactList) throws SQLException {
        Contact obj = new Contact();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setPhoneNumber(rs.getString("PhoneNumber"));
        obj.setGroupId(rs.getInt("GroupId"));
        obj.setContactList(contactList);
        return obj;
    }

    private ContactList instantiateContactList(ResultSet rs) throws SQLException {
        ContactList contactList = new ContactList();
        contactList.setId(rs.getInt("GroupId"));
        contactList.setGroupName(rs.getString("GroupName"));
        return contactList;
    }


    @Override
    public List<Contact> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT contact.*, contactlist.groupName as GroupName, contactlist.id as GroupId " +
                            "FROM contact INNER JOIN contactlist ON contact.GroupId = contactlist.id " +
                            "ORDER BY Name");

            rs = st.executeQuery();

            List<Contact> list = new ArrayList<>();
            Map<Integer, ContactList> map = new HashMap<>();

            while (rs.next()) {
                ContactList contactList = map.get(rs.getInt("GroupId"));

                if (contactList == null) {
                    contactList = instantiateContactList(rs);
                    map.put(rs.getInt("contactlist.id"), contactList);
                }
                Contact obj = instantiateContact(rs, contactList);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public List<Contact> findByContactList(ContactList contactList) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT contact.*, contactlist.groupName as GroupName, contactlist.id as GroupId " +
                            "FROM contact INNER JOIN contactlist " +
                            "ON contact.GroupId = contactlist.id " +
                            "WHERE GroupId = ? " +
                            "ORDER BY Name"
            );

            st.setInt(1, contactList.getId());
            rs = st.executeQuery();

            List<Contact> list = new ArrayList<>();
            Map<Integer, ContactList> map = new HashMap<>();

            while (rs.next()) {
                ContactList cl = map.get(rs.getInt("GroupId"));

                if (cl == null) {
                    cl = instantiateContactList(rs);
                    map.put(rs.getInt("GroupId"), cl);
                }

                Contact obj = instantiateContact(rs, cl);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
