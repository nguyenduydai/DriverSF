package com.example.taixesf.dao;

import com.example.taixesf.model.RoleObject;
import com.example.taixesf.model.WorkingshiftObject;

import java.sql.Timestamp;
import java.util.ArrayList;


public interface WorkingshiftFunction extends GenericDao<WorkingshiftObject> {
    public WorkingshiftObject getWorkingshift(int id);
    public WorkingshiftObject getWorkingshift(String name);
    public ArrayList<WorkingshiftObject> getWorkingshifts(WorkingshiftObject similar, int at, byte total);
    public ArrayList<WorkingshiftObject> getWorkingshifts();
    public ArrayList<WorkingshiftObject> getWorkingshifts(Timestamp time,String similar);
    public ArrayList<WorkingshiftObject> getWorkingshiftsByDate(Timestamp t);
    public boolean addWorkingshift(WorkingshiftObject item);
    public boolean editWorkingshift(WorkingshiftObject item);
    public boolean delWorkingshift(WorkingshiftObject item);
}