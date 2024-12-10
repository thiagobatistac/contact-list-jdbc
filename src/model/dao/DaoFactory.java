package model.dao;

import db.DB;
import model.dao.impl.ContactDaoJDBC;

public class DaoFactory {

    public static ContactDao createContactDao(){
        return new ContactDaoJDBC(DB.getConnection());
    }

    public static ContactListDao createContactListDao(){
        return new ContactListDao(DB.getConnection());
        }
    }

