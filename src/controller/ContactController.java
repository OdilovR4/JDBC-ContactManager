package controller;

import contactManager.Contact;
import service.ContactService;
import util.DataBaseUtil;

import java.util.Scanner;

public class ContactController {
    private boolean b =true;
    private ContactService contactService = new ContactService();
    Scanner strScanner = new Scanner(System.in);
    Scanner numScanner = new Scanner(System.in);

    public void start() {
        DataBaseUtil.createTable();
        while (b) {
            showMenu();
            switch (getAction()) {
                case 1-> addContact();
                case 2 -> contactList();
                case 3 -> deleteContact();
                case 4 -> searchContact();
                case 0 -> exit();
            }

        }

        System.out.println("thanks for your action in contact management");
    }

    public void showMenu() {

        System.out.println("1: Add contact");
        System.out.println("2: Contact list");
        System.out.println("3: Delete contact");
        System.out.println("4: Search contact");
        System.out.println("0: Exit");
    }

    public int getAction() {
        System.out.print("enter action : ");
        return numScanner.nextInt();
    }

    public void exit() {
        b = false;
    }

    public void addContact(){

        System.out.print("Enter name : ");
        String name = strScanner.next();

        System.out.print("Enter surname : ");
        String surname = strScanner.next();

        System.out.print("Enter phone : ");
        String phone = strScanner.next();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);

        contactService.addContact(contact);
    }

    public void contactList(){
        contactService.contactList();
    }

    public void deleteContact(){
        System.out.print("Enter number : ");
        String phone  = strScanner.next();

        contactService.deleteContact(phone);
    }

    public void searchContact(){
        System.out.print("Enter query : ");
        String query  = strScanner.next();

        contactService.searchContact(query);

    }

}
