package com.example.taixesf.dao;

import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.RoleObject;

import java.util.ArrayList;

public interface AreaFunction extends GenericDao<AreaObject> {
    public AreaObject getArea(int id);
    public AreaObject getAreaByCode(String code);
    public ArrayList<AreaObject> getAreasByName(String name);
    public ArrayList<AreaObject> getAreas(AreaObject similar, int at, byte total);
    public boolean addArea(AreaObject item);
    public boolean editArea(AreaObject item);
    public boolean delArea(AreaObject item);
}