package main.Service;

public interface UserServiceInterface {
    public boolean checkUser(String cnie);
    public void createUser(String lname,String fname,String cnie,String password);
}
