package service;
import contactManager.Contact;
import repository.ContactRepository;

import java.util.List;


public class ContactService {
    ContactRepository cr = new ContactRepository();

    public void addContact(Contact contact) {
        Contact check = cr.getByPhone(contact.getPhone());
        if (check != null) {
            System.out.println("this phone was already existed");
            return;
        }
        boolean bool = cr.addContact(contact);
        if (bool) {
            System.out.println("contact added ");
        } else {
            System.out.println("Something went wrong");
        }

    }

    public void contactList() {
        List<Contact> contactList = cr.getList();
        System.out.println("          CONTACT MEMBERS ");
        System.out.println("name         | surname      | phone        ");
        System.out.println("-------------|--------------|---------------");
        for (Contact ct : contactList) {
            System.out.printf("%-13s| %-13s| %-13s\n", ct.getName(), ct.getSurname(), ct.getPhone());
        }
    }

    public void deleteContact(String phone) {
        int result = cr.delete(phone);
        if (result == 0) {
            System.out.println("contact has not been found");
        } else {
            System.out.println(result + " Contact deleted");
        }
    }


    public void searchContact(String query) {
        List<Contact> contactList = cr.search(query);
        System.out.println("          SEARCHED CONTACT  ");
        System.out.println("name         | surname      | phone        ");
        System.out.println("-------------|--------------|---------------");
        for (Contact ct : contactList) {
            System.out.printf("%-13s| %-13s| %-13s\n", ct.getName(), ct.getSurname(), ct.getPhone());
        }
    }

}
