package model.dao;

import db.DB;
import model.dao.impl.ContactDaoJDBC;
import model.dao.impl.ContactListDaoJDBC;

public class DaoFactory {

    public static ContactDao createContactDao(){
        return new ContactDaoJDBC(DB.getConnection());
    }

    public static ContactListDao createContactListDao(){
        return new ContactListDaoJDBC(DB.getConnection());
    }
}

