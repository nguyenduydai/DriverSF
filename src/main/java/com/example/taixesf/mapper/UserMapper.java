package com.example.taixesf.mapper;

import com.example.taixesf.model.UserObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserObject>{
    public UserObject mapRow(ResultSet rs) {
        UserObject item=new UserObject();
        try {
            item.setId(rs.getInt("user_id"));
            item.setUser_name(rs.getString("user_name"));
            item.setUser_password(rs.getString("user_password"));
            item.setUser_email(rs.getString("user_email"));
            item.setUser_sex(rs.getString("user_sex"));
            item.setUser_age(rs.getInt("user_age"));
            item.setUser_phone(rs.getString("user_phone"));
            item.setUser_area_id(rs.getInt("user_area_id"));
            item.setUser_hometown(rs.getString("user_hometown"));
            item.setUser_image(rs.getString("user_image"));
            item.setUser_role_id(rs.getInt("user_role_id"));
            item.setUser_status(rs.getInt("user_status"));
            item.setUser_salary(rs.getInt("user_salary"));
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
