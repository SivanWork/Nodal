package com.example.ranad.nodalsystems.interfaces;

import com.example.ranad.nodalsystems.data_holder.UserData;
import com.example.ranad.nodalsystems.model.User;

import java.util.ArrayList;


public interface UserAction {
    public void readUser(int userId);

    public void fetchUser(User user);


    public void switchToEditUser(int user);

    public ArrayList<UserData> readAllUsers();

    public User getUser();

    public void createUser(User user);


    public void updateUser(User user);


    //   public void deleteUser(User user);

    void deleteUser(User user);
}
