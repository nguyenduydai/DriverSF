package com.example.taixesf.dao.implement;

import com.example.taixesf.dao.RoleFunction;
import com.example.taixesf.dao.WorkingshiftFunction;
import com.example.taixesf.mapper.RoleMapper;
import com.example.taixesf.mapper.WorkingshiftMapper;
import com.example.taixesf.model.RoleObject;
import com.example.taixesf.model.WorkingshiftObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WorkingshiftFunctionImpl  extends AbstractDao<WorkingshiftObject> implements WorkingshiftFunction {
    @Override
    public  WorkingshiftObject getWorkingshift(int id) {
        String sql="SELECT * FROM tblworkingshift WHERE workingshift_id=?";
        List<WorkingshiftObject> workingshifts= query(sql,new WorkingshiftMapper(),id);
        return workingshifts.isEmpty() ? null : workingshifts.get(0);
    }
    public WorkingshiftObject getWorkingshift(String name) {
        String sql="SELECT * FROM tblworkingshift WHERE workingshift_name=?";
        List<WorkingshiftObject> workingshifts= query(sql,new WorkingshiftMapper(),name);
        return workingshifts.isEmpty() ? null : workingshifts.get(0);
    }

    @Override
    public ArrayList<WorkingshiftObject> getWorkingshifts(WorkingshiftObject similar, int at, byte total) {
        String sql="SELECT * FROM tblworkingshift ORDER BY workingshift_id ASC LIMIT "+at+", "+total;
        ArrayList<WorkingshiftObject> listworkingshift = (ArrayList<WorkingshiftObject>) query(sql,new WorkingshiftMapper());
        return listworkingshift;
    }

    @Override
    public ArrayList<WorkingshiftObject> getWorkingshifts() {
        String sql="SELECT * FROM tblworkingshift ORDER BY workingshift_id ASC ";
        ArrayList<WorkingshiftObject> listworkingshift = (ArrayList<WorkingshiftObject>) query(sql,new WorkingshiftMapper());
        return listworkingshift;
    }


    public ArrayList<WorkingshiftObject> getWorkingshifts(Timestamp time,String similar) {
        String sql="SELECT * FROM tblworkingshift WHERE workingshift_createddate = ? AND "+similar;
        ArrayList<WorkingshiftObject> listworkingshift = (ArrayList<WorkingshiftObject>) query(sql,new WorkingshiftMapper(),time);
        return listworkingshift;
    }

    @Override
    public ArrayList<WorkingshiftObject> getWorkingshiftsByDate(Timestamp t) {
        String sql="SELECT * FROM tblworkingshift WHERE workingshift_date=?";
        ArrayList<WorkingshiftObject> workingshifts= (ArrayList<WorkingshiftObject>) query(sql,new WorkingshiftMapper(),t);
        return workingshifts;
    }

    @Override
    public boolean addWorkingshift(WorkingshiftObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO tblworkingshift ");
        sql.append("(workingshift_name, workingshift_date, ");
        sql.append("workingshift_area_id, workingshift_drivernumber, workingshift_money, workingshift_createddate, ");
        sql.append("workingshift_createdby, workingshift_modifieddate, workingshift_modifiedby)");
        sql.append("VALUES (?,?,?,?,?,?,?,?,?)");
        return  update(sql.toString(), item.getWorkingshift_name(), item.getWorkingshift_date(),
                item.getWorkingshift_area_id(),item.getWorkingshift_drivernumber(),item.getWorkingshift_money(),
                item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy());
    }

    @Override
    public boolean editWorkingshift(WorkingshiftObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("UPDATE tblworkingshift SET workingshift_name=?, workingshift_date=?, ");
        sql.append("workingshift_area_id=?, workingshift_drivernumber=?, workingshift_money=?, ");
        sql.append("workingshift_createddate=?, workingshift_createdby=?, workingshift_modifieddate=?, ");
        sql.append("workingshift_modifiedby=? WHERE workingshift_id=?");
        return  update(sql.toString(), item.getWorkingshift_name(), item.getWorkingshift_date(),
                item.getWorkingshift_area_id(),item.getWorkingshift_drivernumber(),item.getWorkingshift_money(),
                item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy(),item.getId());
    }

    @Override
    public boolean delWorkingshift(WorkingshiftObject item) {
        String sql="DELETE FROM tblworkingshift WHERE workingshift_id=?";
        return update(sql,item.getId());
    }
}