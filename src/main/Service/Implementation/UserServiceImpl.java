package main.Service.Implementation;

import main.Entity.User;
import main.Repository.UserRepositoryInterface;
import main.Service.UserServiceInterface;

public class UserServiceImpl implements UserServiceInterface {
    private UserRepositoryInterface user;

    public UserServiceImpl(UserRepositoryInterface user) {
        this.user = user;
    }

    @Override
    public boolean checkUser(String cnie) {
        boolean use = user.checkUser(cnie);
        return user != null;
    }
@Override
    public void createUser(String lname, String fname, String cnie, String password){
        if (lname == null || fname == null || cnie == null || password == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        if (user.checkUser(cnie)) {
            throw new RuntimeException("User with this CNIE already exists");
        }

        user.createUser(lname, fname, cnie,password);
    }


    };

