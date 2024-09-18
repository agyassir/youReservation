package main.Service;

import main.Entity.Customer;

public interface UserServiceInterface {
    public Customer checkUser(String cnie);
    public void createUser(String lname,String fname,String cnie,String password);
}
