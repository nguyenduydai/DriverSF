package com.example.taixesf.dao;

import com.example.taixesf.model.RoleObject;


import java.util.ArrayList;

public interface RoleFunction extends GenericDao<RoleObject> {
    public RoleObject getRole(int id);
    public RoleObject getRole(String name);
    public ArrayList<RoleObject> getRoles(RoleObject similar, int at, byte total);
    public boolean addRole(RoleObject item);
    public boolean editRole(RoleObject item);
    public boolean delRole(RoleObject item);
}
