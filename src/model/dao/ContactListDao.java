package model.dao;

import model.entities.ContactList;

import java.util.List;

public interface ContactListDao {

    void insert (ContactList obj);

    void update (ContactList obj);

    void deleteById (Integer id);

    ContactList findById (Integer id);

    List<ContactList> findAll();

}
