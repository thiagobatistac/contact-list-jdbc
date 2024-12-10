package Test;

import model.dao.ContactDao;
import model.dao.DaoFactory;
import model.entities.Contact;
import model.entities.ContactList;

import java.util.List;

public class ContactDaoJDBC_MethodsTest {
    public static void main(String[] args) {

        ContactDao contactDao = DaoFactory.createContactDao();

        /*
        //insert test
        Contact newContact = new Contact(null, "Netinho", "netinho@gmail.com", "(22)997504368", 1);
        contactDao.insert(newContact);
        System.out.println("Contact inserted successfully! New id = " + newContact.getId());


        //findById test
        Contact contact = contactDao.findById(1);
        System.out.println(contact);


        //update test
        Contact contact = contactDao.findById(1);
        contact.setName("Thiago");
        contactDao.update(contact);
        System.out.println("Update completed!");


        //deleteById test
        Contact contact = contactDao.deleteById(3);
        System.out.println("Delete completed!");


        //findAll test
        ContactList contactList = new ContactList();
        List<Contact> list = contactDao.findAll();

        for (Contact obj : list) {
            System.out.println(obj);
        }

        */
    }
}
