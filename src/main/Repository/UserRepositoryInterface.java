package main.Repository;

import main.Entity.Customer;

public interface UserRepositoryInterface {
    public Customer checkUser(String cnie);
    public void createUser(String lname,String fname,String cnie,String password);
}
