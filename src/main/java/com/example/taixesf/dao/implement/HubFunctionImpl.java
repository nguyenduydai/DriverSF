package com.example.taixesf.dao.implement;

import com.example.taixesf.dao.AreaFunction;
import com.example.taixesf.dao.HubFunction;
import com.example.taixesf.mapper.AreaMapper;
import com.example.taixesf.mapper.HubMapper;
import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.HubObject;

import java.util.ArrayList;
import java.util.List;

public class HubFunctionImpl extends AbstractDao<HubObject> implements HubFunction {
    @Override
    public HubObject getHub(int id) {
        String sql="SELECT * FROM tblhub WHERE hub_id=?";
        List<HubObject> hubs= query(sql,new HubMapper(),id);
        return hubs.isEmpty() ? null : hubs.get(0);
    }
    @Override
    public ArrayList<HubObject> getHubByUser(int id) {
        String sql="SELECT * FROM tblhub WHERE hub_user_id=?";
        ArrayList<HubObject> hubs= (ArrayList<HubObject>) query(sql,new HubMapper(),id);
        return hubs;
    }

    @Override
    public ArrayList<HubObject> getHubByWorkingshift(int id) {
        String sql="SELECT * FROM tblhub WHERE hub_workingshift_id=?";
        ArrayList<HubObject> hubs= (ArrayList<HubObject>) query(sql,new HubMapper(),id);
        return hubs;
    }

    @Override
    public HubObject getHubByUserAndWork(int id1,int id2) {
        String sql="SELECT * FROM tblhub WHERE hub_user_id=? AND hub_workingshift_id=?";
        ArrayList<HubObject> hubs= (ArrayList<HubObject>) query(sql,new HubMapper(),id1,id2);
        return hubs.isEmpty() ? null : hubs.get(0);
    }


    @Override
    public ArrayList<HubObject> getHubByWorkingShift(int id) {
        String sql="SELECT * FROM tblhub WHERE hub_workingshift_id=?";
        ArrayList<HubObject> hubs=(ArrayList<HubObject>)  query(sql,new HubMapper(),id);
        return hubs;
    }
    @Override
    public HubObject getHub(String name) {
        String sql="SELECT * FROM tblhub WHERE ?";
        List<HubObject> hubs= query(sql,new HubMapper(),name);
        return hubs.isEmpty() ? null : hubs.get(0);
    }


    @Override
    public ArrayList<HubObject> getHubs(HubObject similar, int at, byte total) {
        String sql="SELECT * FROM tblhub ORDER BY hub_id DESC LIMIT "+at+", "+total;
        ArrayList<HubObject> listhub = (ArrayList<HubObject>) query(sql,new HubMapper());
        return listhub;
    }

    @Override
    public boolean addHub(HubObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO tblhub ");
        sql.append("(hub_name, hub_user_id, hub_workingshift_id, hub_ordernumber, hub_createddate, ");
        sql.append("hub_createdby, hub_modifieddate, hub_modifiedby) VALUES (?,?,?,?,?,?,?,?)");
        return  update(sql.toString(), item.getHub_name(), item.getHub_user_id(),item.getHub_workingshift_id(),
                item.getHub_ordernumber(),item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy());
    }

    @Override
    public boolean editHub(HubObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("UPDATE tblhub SET hub_name=?, hub_user_id=?, hub_workingshift_id=?, hub_ordernumber=?, ");
        sql.append("hub_createddate=?, hub_createdby=?, hub_modifieddate=?, hub_modifiedby=? WHERE hub_id=?");
        return  update(sql.toString(), item.getHub_name(), item.getHub_user_id(),item.getHub_workingshift_id(),
                item.getHub_ordernumber(),item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(),
                item.getModifiedBy(), item.getId());
    }

    @Override
    public boolean delHub(HubObject item) {
        String sql="DELETE FROM tblhub WHERE hub_id=?";
        return update(sql,item.getId());
    }
}
