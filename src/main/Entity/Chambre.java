package main.Entity;

public class Chambre {
    private static  int id=0;

    private String type;
    private int quantity;
    private Hotel hotel;
    private double prix;

    public Chambre( Hotel hotel, String type,int quantity,double prix) {
        this.id=++id;
        this.hotel = hotel;
        this.type=type;
        this.quantity = quantity;
        this.prix=prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotelName() {
        return hotel.getName();
    }

    public String getHotelLocation() {
        return hotel.getLocation();
    }

    public double getHotelRate(){
        return hotel.getRate();
    }

    public void setHotel(String name,String location) {
        this.hotel = new Hotel(name, location);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Hotel getHotel() {
        return hotel;
    }

}
