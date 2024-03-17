package lk.ijse.entity.tm;

public class TransactionTM {

    private String id;

    private String user;

    private String branch;

    private String book;

    private String date;

    private String  contact;

    public TransactionTM(String id, String user, String branch, String book, String date, String contact) {
        this.id = id;
        this.user = user;
        this.branch = branch;
        this.book = book;
        this.date = date;
        this.contact = contact;
    }

    public TransactionTM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "TransactionTM{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", branch='" + branch + '\'' +
                ", book='" + book + '\'' +
                ", date='" + date + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
