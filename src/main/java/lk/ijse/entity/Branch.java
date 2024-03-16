package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Branch {

    @Id
    private String id;
    private String branch_name;

    private String manager;

    private String contact;

    public Branch() {
    }

    public Branch(String id, String branch_name, String manager, String contact) {
        this.id = id;
        this.branch_name = branch_name;
        this.manager = manager;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", branch_name='" + branch_name + '\'' +
                ", manager='" + manager + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
