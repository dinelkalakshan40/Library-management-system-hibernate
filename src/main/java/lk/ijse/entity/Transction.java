package lk.ijse.entity;

public class Transction {

    private String id;
    private String user;

    private Branch branch;


    private Book book;

    private String date;

    private String  contact;

    public Transction(String id, String user, Branch branch, Book book, String date, String contact) {
        this.id = id;
        this.user = user;
        this.branch = branch;
        this.book = book;
        this.date = date;
        this.contact = contact;
    }

    public Transction() {
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
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
        return "Transaction{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", branch=" + branch +
                ", book=" + book +
                ", date='" + date + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
