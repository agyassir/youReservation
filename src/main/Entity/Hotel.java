package main.Entity;

public class Hotel {

    private int id;
    private String name;
    private String location;
    private double rate;

    public Hotel(String name, String location) {
        this.id = id++;
        this.name = name;
        this.location = location;
        this.rate = 0;
    }

    public Hotel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
