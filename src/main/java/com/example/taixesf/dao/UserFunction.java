package com.example.taixesf.dao;

import com.example.taixesf.model.UserObject;

import java.util.ArrayList;

public interface UserFunction extends GenericDao<UserObject> {
    public UserObject getUser(int id);
    public UserObject getUser(String userphone, String userpass);
    public ArrayList<UserObject> getUsers(UserObject similar, int at, byte total);
    public ArrayList<UserObject> getUsers(String similar);
    public boolean addUser(UserObject item);
    public boolean editUser(UserObject item);
    public boolean delUser(UserObject item);
    public boolean isExisting(UserObject item );

}
