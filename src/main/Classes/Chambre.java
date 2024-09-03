package main.Classes;

public class Chambre {
    private static  int id=0;
    private String type;
    
    private int quantity;
    private Hotel hotel;

    public Chambre(String type, Hotel hotel, int quantity) {
        this.id=++id;
        this.type = type;
        this.hotel = hotel;
        this.quantity = quantity;
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
