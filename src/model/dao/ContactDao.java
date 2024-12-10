package model.dao;

import model.entities.Contact;
import model.entities.ContactList;

import java.util.List;

public interface ContactDao {

    void insert(Contact obj);

    void update(Contact obj);

    Contact deleteById(Integer id);

    Contact findById(Integer id);

    List<Contact> findAll();

    List<Contact> findByContactList(ContactList contactList);
}

