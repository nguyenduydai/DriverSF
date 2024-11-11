package com.example.taixesf.dao.implement;

import com.example.taixesf.dao.AreaFunction;

import com.example.taixesf.mapper.AreaMapper;

import com.example.taixesf.model.AreaObject;


import java.util.ArrayList;
import java.util.List;

public class AreaFunctionImpl  extends AbstractDao<AreaObject> implements AreaFunction {
    @Override
    public AreaObject getArea(int id) {
        String sql="SELECT * FROM tblarea WHERE area_id=?";
        List<AreaObject> areas= query(sql,new AreaMapper(),id);
        return areas.isEmpty() ? null : areas.get(0);
    }

    @Override
    public AreaObject getAreaByCode(String code) {
        String sql="SELECT * FROM tblarea WHERE area_code=?";
        List<AreaObject> areas= query(sql,new AreaMapper(),code);
        return areas.isEmpty() ? null : areas.get(0);
    }

    public ArrayList<AreaObject> getAreasByName(String name) {
        String sql="SELECT * FROM tblarea WHERE area_name = ?";
        ArrayList<AreaObject> listarea = (ArrayList<AreaObject>) query(sql,new AreaMapper(),name);
        return listarea;
    }

    @Override
    public ArrayList<AreaObject> getAreas(AreaObject similar, int at, byte total) {
        String sql="SELECT * FROM tblarea ORDER BY area_id ASC LIMIT "+at+", "+total;
        ArrayList<AreaObject> listarea = (ArrayList<AreaObject>) query(sql,new AreaMapper());
        return listarea;
    }

    @Override
    public boolean addArea(AreaObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO tblarea ");
        sql.append("(area_name, area_code, area_createddate, ");
        sql.append("area_createdby, area_modifieddate, area_modifiedby)");
        sql.append("VALUES (?,?,?,?,?,?)");
        return  update(sql.toString(), item.getArea_name(), item.getArea_code(),
                item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy());
    }

    @Override
    public boolean editArea(AreaObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("UPDATE tblarea SET area_name=?, area_code=?, ");
        sql.append("area_createddate=?, area_createdby=?, area_modifieddate=?, area_modifiedby=? WHERE area_id=?");
        return  update(sql.toString(), item.getArea_name(), item.getArea_code(), item.getCreatedDate(),
                item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy(),item.getId());
    }

    @Override
    public boolean delArea(AreaObject item) {
        String sql="DELETE FROM tblarea WHERE area_id=?";
        return update(sql,item.getId());
    }
}