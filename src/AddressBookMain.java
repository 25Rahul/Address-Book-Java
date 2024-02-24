import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

class Contact{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public Contact(String firstName ,String lastName,String address,String city,String state,String zip,String phoneNumber,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName =lastName;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address =address;
    }
    public  String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getState(){
        return state;
    }
    public void setState(){
        this.state =state;
    }
    public String getZip(){
        return zip;
    }
    public void setZip(String zip){
        this.zip = zip;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String toString(){
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ",lastName='" + lastName + '\'' +
                ",address='" + address + '\'' +
                ",city='" + city + '\'' +
                ",state='" + state + '\''+
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';

    }
}
class AddressBook {
    private List<Contact> contacts;
    public AddressBook() {
        contacts = new ArrayList<>();
    }
    public void addContact(Contact contact) {
        for(Contact existingContact: contacts){
            if(existingContact.getFirstName().equals(contact.getFirstName()) && existingContact.getLastName().equals(contact.getLastName())){
                System.out.println("Contact already exists.");
                return;
            }
        }
        contacts.add(contact);
        System.out.println("Contact added successfully.");
    }
    public void editContact(String firstName,String lastName,Contact newContact){
        for(int i=0;i<contacts.size();i++){
            Contact contact = contacts.get(i);
            if(contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)){
                contacts.set(i,newContact);
                System.out.println("Contact updated Sucessfully");
                return;
            }
        }
        System.out.println("Contact not found.");
    }
    public  void deleteContact(String firstName,String lastName){
        Iterator<Contact> iterator = contacts.iterator();
        while(iterator.hasNext()){
            Contact contact = iterator.next();
            if(contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)){
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

    public void sortContactsByZip(){
        contacts.sort(Comparator.comparing(Contact::getState));
    }
    public List<Contact> getContacts() {
        return contacts;
    }

    public void writeToFile(String fileName){
        try(FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(contacts);
            System.out.println("Address book written to file" +fileName);
        }
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String fileName){
        try(FileInputStream fis = new FileInputStream(fileName);
           ObjectInputStream ois = new ObjectInputStream(fis)){
           contacts = (List<Contact>) ois.readObject();
            System.out.println("Address book read from file: " + fileName);
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System");
        AddressBook addressBook = new AddressBook();
        Map<String,AddressBook> addressBooks = new HashMap<>();
        Map<String, List<Contact>> cityMap = new HashMap<>();
        Map<String, List<Contact>> stateMap = new HashMap<>();
        Scanner scanner = new Scanner(System .in);

        while(true) {
            System.out.println("Enter 'addbook' to add a new address book,'add' to add a new contact,'edit' to edit an existing contact,'delete' to delete a contact,'search' to search for a person,'view' to view persons by city or state,'count' to get count by city or state, 'sort' to sort contacts by name,'sortcity' to sort contacts by city,'sortstate' to sort contacts by state, 'sortzip' to sort contacts by zip,'write' to write address book to a file, 'read' to read address book from a file, or 'exit' to quit:");
            String input = scanner.nextLine();
            if ("addbook".equalsIgnoreCase(input)) {
                System.out.println("Enter the name of the new address book:");
                String addressBookName = scanner.nextLine();
                AddressBook newAddressBook = new AddressBook();
                addressBooks.put(addressBookName, newAddressBook);
                System.out.println("Address book '" + addressBookName + "' added successfully.");
            } else if ("add".equalsIgnoreCase(input)) {

                System.out.println("Enter first name:");
                String firstName = scanner.nextLine();

                System.out.println("Enter last name:");
                String lastName = scanner.nextLine();

                System.out.println("Enter address:");
                String address = scanner.nextLine();

                System.out.println("Enter city:");
                String city = scanner.nextLine();

                System.out.println("Enter state:");
                String state = scanner.nextLine();

                System.out.println("Enter zip code:");
                String zip = scanner.nextLine();

                System.out.println("Enter phone number:");
                String phoneNumber = scanner.nextLine();

                System.out.println("Enter Email:");
                String email = scanner.nextLine();

                Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
                addressBook.addContact(contact);
                cityMap.computeIfAbsent(city, k -> new ArrayList<>()).add(contact);
                stateMap.computeIfAbsent(state, k -> new ArrayList<>()).add(contact);

            } else if ("sort".equalsIgnoreCase(input)) {
                addressBook.sortContactsByName();
                System.out.println("Contacts sorted by name.");
            } else if ("sortcity".equalsIgnoreCase(input)) {
                addressBook.sortContactsByCity();
                System.out.println("Contacts sorted by city.");
            } else if ("sortstate".equalsIgnoreCase(input)) {
                addressBook.sortContactsByState();
                System.out.println("Contacts sorted by state.");
            } else if ("sortzip".equalsIgnoreCase(input)) {
                addressBook.sortContactsByZip();
                System.out.println("Contacts sorted by zip.");
            } else if ("edit".equalsIgnoreCase(input)) {
                System.out.println("Enter first name of contact to edit:");
                String firstName = scanner.nextLine();
                System.out.println("Enter last name of contact to edit:");
                String lastName = scanner.nextLine();

                System.out.println("Enter new first name:");
                String newFirstName = scanner.nextLine();

                System.out.println("Enter new last name:");
                String newLastName = scanner.nextLine();

                System.out.println("Enter new address:");
                String newAddress = scanner.nextLine();

                System.out.println("Enter new city:");
                String newCity = scanner.nextLine();

                System.out.println("Enter new state:");
                String newState = scanner.nextLine();

                System.out.println("Enter new zip code:");
                String newZip = scanner.nextLine();

                System.out.println("Enter new phone number:");
                String newPhoneNumber = scanner.nextLine();

                System.out.println("Enter new email:");
                String newEmail = scanner.nextLine();

                Contact newContact = new Contact(newFirstName,newLastName,newAddress, newCity, newState, newZip, newPhoneNumber, newEmail);
                addressBook.editContact(firstName,lastName,newContact);

            } else if ("delete".equalsIgnoreCase(input)) {
                System.out.println("Enter first name of contact to delete:");
                String firstName = scanner.nextLine();
                System.out.println("Enter last name of contact to delete:");
                String lastName = scanner.nextLine();

                addressBook.deleteContact(firstName,lastName);
            }else if ("search".equalsIgnoreCase(input)) {
                System.out.println("Enter 'city' to search by city or 'state' to search by state:");
                String searchType = scanner.nextLine();
                if("city".equalsIgnoreCase(searchType)){
                    System.out.println("Enter city name:");
                    String city = scanner.nextLine();
                    addressBooks.values().stream()
                            .flatMap(addressBook1 -> addressBook.getContacts().stream())
                            .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                            .forEach(System.out::println);
                }
                else if ("state".equalsIgnoreCase(searchType)) {
                    System.out.println("Enter state name:");
                    String state = scanner.nextLine();

                    addressBooks.values().stream()
                            .flatMap(addressBook1 -> addressBook.getContacts().stream())
                            .filter(contact -> contact.getState().equalsIgnoreCase(state))
                            .forEach(System.out::println);
                } else {
                    System.out.println("Invalid search type.");
                }
            }else if ("write".equalsIgnoreCase(input)) {
                System.out.println("Enter file name to write:");
                String fileName = scanner.nextLine();
                addressBook.writeToFile(fileName);
            } else if ("read".equalsIgnoreCase(input)) {
                System.out.println("Enter file name to read:");
                String fileName = scanner.nextLine();
                addressBook.readFromFile(fileName);
            }else if ("view".equalsIgnoreCase(input)) {
                System.out.println("Enter 'city' to view by city or 'state' to view by state:");
                String viewType = scanner.nextLine();

                if("city".equalsIgnoreCase(viewType)){
                    System.out.println("Enter city name:");
                    String city = scanner.nextLine();
                    if(cityMap.containsKey(city)){
                        System.out.println("Persons in " + city + ":");
                        cityMap.get(city).forEach(System.out::println);
                    }
                    else{
                        System.out.println("No persons found for city: " + city);
                    }
                }
                else if ("state".equalsIgnoreCase(viewType)) {
                    System.out.println("Enter state name:");
                    String state = scanner.nextLine();

                    if (stateMap.containsKey(state)) {
                        System.out.println("Persons in " + state + ":");
                        stateMap.get(state).forEach(System.out::println);
                    } else {
                        System.out.println("No persons found for state: " + state);
                    }
                } else {
                    System.out.println("Invalid view type.");
                }
            } else if ("count".equalsIgnoreCase(input)) {
                System.out.println("Enter 'city' to get count by city or 'state' to get count by state:");
                String countType = scanner.nextLine();
                if ("city".equalsIgnoreCase(countType)) {
                    System.out.println("Enter city name:");
                    String city = scanner.nextLine();
                    long count = cityMap.entrySet().stream()
                            .filter(entry->entry.getKey().equalsIgnoreCase(city))
                            .flatMap(entry-> entry.getValue().stream())
                            .count();
                    System.out.println("Number of contacts in " + city + ": " + count);
                } else if("state".equalsIgnoreCase(countType)){
                    System.out.println("Enter state name:");
                    String state = scanner.nextLine();
                    long count = cityMap.entrySet().stream()
                            .filter(entry-> entry.getKey().equalsIgnoreCase(state))
                            .flatMap(entry-> entry.getValue().stream())
                            .count();
                    System.out.println("Number of contacts in " + state + ": " + count);
                    }else {
                    System.out.println("Invalid count type.");
                }
            } else if ("exit".equalsIgnoreCase(input)) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        System.out.println("Address Books:");
        for(String addressBookName : addressBooks.keySet()) {
            AddressBook address = addressBooks.get(addressBookName);
            System.out.println("Address Book: " + addressBookName);
            System.out.println("Contacts:");
            for (Contact contact : addressBook.getContacts()) {
                System.out.println(contact);
            }
            System.out.println();
        }
        scanner.close();
    }
}