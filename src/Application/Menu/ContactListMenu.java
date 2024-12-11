package Application.Menu;

import db.DbException;
import model.dao.ContactListDao;
import model.entities.ContactList;

import java.util.List;
import java.util.Scanner;

public class ContactListMenu {
    public static void menuContactList(Scanner input, ContactListDao contactListDao) {
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== Contact List Menu ===");
            System.out.println("1 - Create new contact group");
            System.out.println("2 - View a single group of contacts");
            System.out.println("3 - View all existing contact groups");
            System.out.println("4 - Update an existing contact group");
            System.out.println("5 - Delete a contact group");
            System.out.println("0 - Go back to the main menu");
            System.out.print("Choose an option: ");

            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        createContactGroup(input, contactListDao);
                        break;
                    case 2:
                        viewSingleContactGroup(input, contactListDao);
                        break;
                    case 3:
                        viewAllContactGroups(contactListDao);
                        break;
                    case 4:
                        updateContactGroup(input, contactListDao);
                        break;
                    case 5:
                        deleteContactGroup(input, contactListDao);
                        break;
                    case 0:
                        System.out.println("Going back to the main menu...");
                        break;
                    default:
                        System.out.println("Invalid option! Try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                input.next();
            }
        }
    }

    private static void createContactGroup(Scanner input, ContactListDao contactListDao) {
        System.out.print("How many contact groups do you want to create? ");
        if (input.hasNextInt()) {
            int n = input.nextInt();
            input.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Enter the name of the contact group: ");
                String groupName = input.nextLine();

                ContactList newContactGroup = new ContactList(null, groupName);
                try {
                    contactListDao.insert(newContactGroup);
                    System.out.println("Contact group created successfully! ID = " + newContactGroup.getId());
                } catch (DbException e) {
                    System.out.println("Failed to create contact group: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Invalid input! Returning to menu...");
            input.next();
        }
    }

    private static void viewSingleContactGroup(Scanner input, ContactListDao contactListDao) {
        System.out.print("Enter the ID of the contact group to view: ");
        if (input.hasNextInt()) {
            int groupId = input.nextInt();
            input.nextLine();

            ContactList contactList = contactListDao.findById(groupId);
            if (contactList != null) {
                System.out.println("Contact Group: " + contactList);
            } else {
                System.out.println("Contact group not found.");
            }
        } else {
            System.out.println("Invalid input! Returning to menu...");
            input.next();
        }
    }

    private static void viewAllContactGroups(ContactListDao contactListDao) {
        System.out.println("Showing all contact groups:");
        List<ContactList> contactLists = contactListDao.findAll();
        for (ContactList contactList : contactLists) {
            System.out.println(contactList);
        }
    }

    private static void updateContactGroup(Scanner input, ContactListDao contactListDao) {
        System.out.print("Enter the ID of the contact group to update: ");
        if (input.hasNextInt()) {
            int groupId = input.nextInt();
            input.nextLine();

            ContactList contactList = contactListDao.findById(groupId);
            if (contactList != null) {
                System.out.print("Enter a new name for the contact group: ");
                String newGroupName = input.nextLine();

                contactList.setGroupName(newGroupName);
                try {
                    contactListDao.update(contactList);
                    System.out.println("Contact group updated successfully!");
                } catch (DbException e) {
                    System.out.println("Failed to update contact group: " + e.getMessage());
                }
            } else {
                System.out.println("Contact group not found.");
            }
        } else {
            System.out.println("Invalid input! Returning to menu...");
            input.next();
        }
    }

    private static void deleteContactGroup(Scanner input, ContactListDao contactListDao) {
        System.out.print("Enter the ID of the contact group to delete: ");
        if (input.hasNextInt()) {
            int groupId = input.nextInt();
            input.nextLine();

            try {
                contactListDao.deleteById(groupId);
                System.out.println("Contact group deleted successfully!");
            } catch (DbException e) {
                System.out.println("Failed to delete contact group: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid input! Returning to menu...");
            input.next();
        }
    }
}
