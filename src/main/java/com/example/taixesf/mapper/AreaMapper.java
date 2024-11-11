package com.example.taixesf.mapper;

import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.RoleObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaMapper implements RowMapper<AreaObject> {
    @Override
    public AreaObject mapRow(ResultSet rs) {
        AreaObject item=new AreaObject();
        try {
            item.setId(rs.getInt("area_id"));
            item.setArea_name(rs.getString("area_name"));
            item.setArea_code(rs.getString("area_code"));
            item.setCreatedDate(rs.getTimestamp("area_createddate"));
            item.setModifiedDate(rs.getTimestamp("area_modifieddate"));
            item.setCreatedBy(rs.getString("area_createdby"));
            item.setModifiedBy(rs.getString("area_modifiedby"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }
}
