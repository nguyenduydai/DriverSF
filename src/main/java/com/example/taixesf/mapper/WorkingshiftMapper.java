package com.example.taixesf.mapper;

import com.example.taixesf.model.RoleObject;
import com.example.taixesf.model.WorkingshiftObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingshiftMapper implements RowMapper<WorkingshiftObject> {
    @Override
    public WorkingshiftObject mapRow(ResultSet rs) {
        WorkingshiftObject item=new WorkingshiftObject();
        try {
            item.setId(rs.getInt("workingshift_id"));
            item.setWorkingshift_name(rs.getString("workingshift_name"));
            item.setWorkingshift_date(rs.getTimestamp("workingshift_date"));
            item.setWorkingshift_drivernumber(rs.getInt("workingshift_drivernumber"));
            item.setWorkingshift_money(rs.getInt("workingshift_money"));
            item.setWorkingshift_area_id(rs.getInt("workingshift_area_id"));
            item.setCreatedDate(rs.getTimestamp("workingshift_createddate"));
            item.setModifiedDate(rs.getTimestamp("workingshift_modifieddate"));
            item.setCreatedBy(rs.getString("workingshift_createdby"));
            item.setModifiedBy(rs.getString("workingshift_modifiedby"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }
}
