package com.example.taixesf.mapper;

import com.example.taixesf.model.RoleObject;
import com.example.taixesf.model.UserObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<RoleObject>{
    @Override
    public RoleObject mapRow(ResultSet rs) {
        RoleObject item=new RoleObject();
        try {
            item.setId(rs.getInt("role_id"));
            item.setRole_name(rs.getString("role_name"));
            item.setRole_position(rs.getString("role_position"));
            item.setCreatedDate(rs.getTimestamp("user_createddate"));
            item.setModifiedDate(rs.getTimestamp("user_modifieddate"));
            item.setCreatedBy(rs.getString("user_createdby"));
            item.setModifiedBy(rs.getString("user_modifiedby"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }
}
