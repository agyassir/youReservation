package main.Entity;

public class User {
private String cnie;
private String FirstName;
private String LastName;

    public User(String cnie, String firstName, String lastName) {
        this.cnie = cnie;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public String getCnie() {
        return cnie;
    }

    public void setCnie(String cnie) {
        this.cnie = cnie;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
