package Application.Menu;

import db.DbException;
import model.dao.ContactDao;
import model.entities.Contact;

import java.util.List;
import java.util.Scanner;

public class ContactMenu {
    public static void menuContact(Scanner input, ContactDao contactDao) {
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== Contact Menu ===");
            System.out.println("1 - Create new contact");
            System.out.println("2 - Search a contact");
            System.out.println("3 - View all existing contacts");
            System.out.println("4 - Update an existing contact");
            System.out.println("5 - Delete a contact");
            System.out.println("0 - Go back to the main menu");
            System.out.print("Choose an option: ");

            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        createContact(input, contactDao);
                        break;
                    case 2:
                        searchContact(input, contactDao);
                        break;
                    case 3:
                        viewAllContacts(contactDao);
                        break;
                    case 4:
                        updateContact(input, contactDao);
                        break;
                    case 5:
                        deleteContact(input, contactDao);
                        break;
                    case 0:
                        System.out.println("Going back to the main menu...");
                        break;
                    default:
                        System.out.println("Invalid option! Try again...");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                input.next();
            }
        }
    }

    private static void createContact(Scanner input, ContactDao contactDao) {
        System.out.println("Creating new contact...");
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = input.nextLine();
        System.out.print("Group Id: ");

        if (input.hasNextInt()) {
            int groupId = input.nextInt();
            input.nextLine();

            Contact newContact = new Contact(null, name, email, phoneNumber, groupId);
            try {
                contactDao.insert(newContact);
                System.out.println("Contact created successfully!");
            } catch (DbException e) {
                System.out.println("Error while creating contact: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid Group Id!");
            input.next();
        }
    }

    private static void searchContact(Scanner input, ContactDao contactDao) {
        System.out.print("Enter the ID of the contact you are looking for: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            input.nextLine();
            Contact contact = contactDao.findById(id);
            System.out.println(contact != null ? contact : "Contact not found.");
        } else {
            System.out.println("Invalid ID!");
            input.next();
        }
    }

    private static void viewAllContacts(ContactDao contactDao) {
        System.out.println("Viewing all contacts...");
        List<Contact> contacts = contactDao.findAll();
        contacts.forEach(System.out::println);
    }

    private static void updateContact(Scanner input, ContactDao contactDao) {
        System.out.print("Enter the ID of the contact to update: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            input.nextLine();
            Contact contact = contactDao.findById(id);

            if (contact != null) {
                System.out.print("New name (leave blank to keep current): ");
                String newName = input.nextLine();
                System.out.print("New email (leave blank to keep current): ");
                String newEmail = input.nextLine();
                System.out.print("New phone number (leave blank to keep current): ");
                String newPhone = input.nextLine();

                if (!newName.isBlank()) contact.setName(newName);
                if (!newEmail.isBlank()) contact.setEmail(newEmail);
                if (!newPhone.isBlank()) contact.setPhoneNumber(newPhone);

                try {
                    contactDao.update(contact);
                    System.out.println("Contact updated successfully!");
                } catch (DbException e) {
                    System.out.println("Error while updating contact: " + e.getMessage());
                }
            } else {
                System.out.println("Contact not found.");
            }
        } else {
            System.out.println("Invalid ID!");
            input.next();
        }
    }

    private static void deleteContact(Scanner input, ContactDao contactDao) {
        System.out.print("Enter the ID of the contact to delete: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            input.nextLine();
            try {
                contactDao.deleteById(id);
                System.out.println("Contact deleted successfully!");
            } catch (DbException e) {
                System.out.println("Error while deleting contact: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid ID!");
            input.next();
        }
    }
}
