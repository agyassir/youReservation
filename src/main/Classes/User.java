package main.Classes;

public class User {
private int cnie;
private String FirstName;
private String LastName;

    public User(int cnie, String firstName, String lastName) {
        this.cnie = cnie;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public int getCnie() {
        return cnie;
    }

    public void setCnie(int cnie) {
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
