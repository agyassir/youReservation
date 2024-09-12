package main.Repository;

public interface UserRepositoryInterface {
    public boolean checkUser(String cnie);
    public void createUser(String lname,String fname,String cnie,String password);
}
