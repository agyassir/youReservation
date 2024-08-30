package main.Classes;

public class Chambre {
    private int id;
    private String type;
    
    private int quantity;
    private Hotel hotel;

    public Chambre(int id, String type, String name,String location, int quantity) {
        this.id = id;
        this.type = type;
        this.hotel = new Hotel(name,location);
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



}
