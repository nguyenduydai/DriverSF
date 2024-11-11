package com.example.taixesf.dao;

import com.example.taixesf.dao.implement.HubFunctionImpl;
import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.HubObject;

import java.util.ArrayList;

public interface HubFunction extends GenericDao<HubObject> {
    public HubObject getHub(int id);

    public HubObject getHub(String name);
    public ArrayList<HubObject> getHubByUser(int id);
    public ArrayList<HubObject> getHubByWorkingshift(int id);
    public HubObject getHubByUserAndWork(int id1,int id2);
    public ArrayList<HubObject> getHubByWorkingShift(int id);
    public ArrayList<HubObject> getHubs(HubObject similar, int at, byte total);
    public boolean addHub(HubObject item);
    public boolean editHub(HubObject item);
    public boolean delHub(HubObject item);

}
