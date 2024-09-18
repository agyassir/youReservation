package main.Entity;

public class Customer extends User{
private int loyaltyPoints;

    public Customer(String cnie, String firstName, String lastName) {
        super(cnie, firstName, lastName);
    }
    public Customer(){}

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
