import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        for (Contact existingContact : contacts) {
            if (existingContact.getFirstName().equals(contact.getFirstName()) && existingContact.getLastName().equals(contact.getLastName())) {
                System.out.println("Contact already exists.");
                return;
            }
        }
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }

    public void editContact(String firstName, String lastName, Contact newContact) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contacts.set(i, newContact);
                System.out.println("Contact updated Sucessfully");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String firstName, String lastName) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                iterator.remove();
                System.out.println("Contact deleted successfully");
                return;
            }
        }
        System.out.println("Contact not found.");

    }

    public void sortContactsByName() {
        contacts.sort(Comparator.comparing(Contact::getFullName));
    }

    public void sortContactsByCity() {
        contacts.sort(Comparator.comparing(Contact::getCity));
    }

    public void sortContactsByState() {
        contacts.sort(Comparator.comparing(Contact::getState));
    }

    public void sortContactsByZip() {
        contacts.sort(Comparator.comparing(Contact::getState));
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void writeToFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contacts);
            System.out.println("Address book written to file" + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            contacts = (List<Contact>) ois.readObject();
            System.out.println("Address book read from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
