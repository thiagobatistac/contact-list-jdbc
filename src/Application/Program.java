package Application;

import Application.Menu.ContactListMenu;
import Application.Menu.ContactMenu;

import model.dao.ContactDao;
import model.dao.ContactListDao;
import model.dao.DaoFactory;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            ContactListDao contactListDao = DaoFactory.createContactListDao();
            ContactDao contactDao = DaoFactory.createContactDao();

            int mainOption = -1;

            while (mainOption != 0) {
                System.out.println("==== MAIN MENU ====");
                System.out.println("1 - Operations in the ContactList table");
                System.out.println("2 - Operations in the Contact table");
                System.out.println("0 - Quit");
                System.out.print("Choose an option: ");

                if (input.hasNextInt()) {
                    mainOption = input.nextInt();
                    input.nextLine();

                    switch (mainOption) {
                        case 1:
                            ContactListMenu.menuContactList(input, contactListDao);
                            break;
                        case 2:
                            ContactMenu.menuContact(input, contactDao);
                            break;
                        case 0:
                            System.out.println("Finishing the program...");
                            break;
                        default:
                            System.out.println("Invalid option! Try again.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    input.next();
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
