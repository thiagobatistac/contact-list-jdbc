package model.entities;

import java.util.Objects;

public class Contact {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer groupId;

    private ContactList contactList;

    public Contact() {
    }

    public Contact(Integer id, String name, String email, String phoneNumber, Integer groupId, ContactList contactList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.groupId = groupId;
        this.contactList = contactList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public ContactList getContactList() {
        return contactList;
    }

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", groupId=" + groupId +
                ", contactList=" + contactList +
                '}';
    }
}
