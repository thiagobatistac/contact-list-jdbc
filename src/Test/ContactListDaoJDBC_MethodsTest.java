package Test;

import model.dao.ContactListDao;
import model.dao.DaoFactory;
import model.entities.ContactList;

public class ContactListDaoJDBC_MethodsTest {
    public static void main(String[] args) {

        ContactListDao contactListDao = DaoFactory.createContactListDao();

        /*
        //findById test
        ContactList contactList = contactListDao.findById(3);
        System.out.println(contactList);


        //findAll test
        List<ContactList> list = contactListDao.findAll();
        for (ContactList contactList : list){
            System.out.println(contactList);
        }


        //insert test
        ContactList newContactGroupName = new ContactList(null, "colegas");
        contactListDao.insert(newContactGroupName);
        System.out.println("Contact group inserted successfully! New id = " + newContactGroupName.getId());


        //update test
        ContactList contactGroupName = contactListDao.findById(6);
        contactGroupName.setGroupName("Colegas");
        contactListDao.update(contactGroupName);
        System.out.println("Update completed!");


        //deleteById test
        contactListDao.deleteById(6);
        System.out.println("Delete completed!");
        */
    }
}
