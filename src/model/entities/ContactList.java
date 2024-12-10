package model.entities;

import java.util.Objects;

public class ContactList {

    private static final long serialVersionUID = 1L;
    private String groupName;
    private Integer id;

    public ContactList(){
    }

    public ContactList(String name, Integer id) {
        this.groupName = name;
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "ContactList{" +
                "groupName='" + groupName + '\'' +
                ", id=" + id +
                '}';
    }
}
