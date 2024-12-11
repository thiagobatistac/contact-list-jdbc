package model.entities;

import java.util.Objects;

public class ContactList {

    private Integer id;
    private String groupName;

    public ContactList() {
    }

    public ContactList(Integer id, String groupName) {
        this.id = null;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactList that = (ContactList) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ContactList: " +
                "Id: " + id +
                ", Group Name: '" + groupName + '\'';
    }
}

