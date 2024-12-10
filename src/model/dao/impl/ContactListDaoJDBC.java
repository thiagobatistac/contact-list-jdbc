package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ContactListDao;
import model.entities.ContactList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactListDaoJDBC implements ContactListDao {
    private Connection conn;

    public ContactListDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ContactList obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO contactlist " +
                            "(groupName) " +
                            "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getGroupName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(ContactList obj) {
        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE contactlist " +
                            "SET groupName = ? " +
                            "WHERE id = ?");

            st.setString(1, obj.getGroupName());
            st.setInt(2, obj.getId());

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
            st = conn.prepareStatement("DELETE FROM contactlist WHERE id = ? ");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public ContactList findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM contactlist WHERE id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                ContactList contactList = instantiateContactList(rs);
                return contactList;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private ContactList instantiateContactList(ResultSet rs) throws SQLException {
        ContactList contactList = new ContactList();
        contactList.setId(rs.getInt("id"));
        contactList.setGroupName(rs.getString("groupName"));
        return contactList;
    }

    @Override
    public List<ContactList> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM contactlist");

            rs = st.executeQuery();

            List<ContactList> list = new ArrayList<>();

            while (rs.next()) {
                ContactList contactList = instantiateContactList(rs);
                list.add(contactList);
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






