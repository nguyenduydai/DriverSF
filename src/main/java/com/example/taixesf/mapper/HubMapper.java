package com.example.taixesf.mapper;

import com.example.taixesf.model.AreaObject;
import com.example.taixesf.model.HubObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HubMapper  implements RowMapper<HubObject> {
    @Override
    public HubObject mapRow(ResultSet rs) {
        HubObject item=new HubObject();
        try {
            item.setId(rs.getInt("hub_id"));
            item.setHub_name(rs.getString("hub_name"));
            item.setHub_user_id(rs.getInt("hub_user_id"));
            item.setHub_workingshift_id(rs.getInt("hub_workingshift_id"));
            item.setHub_ordernumber(rs.getInt("hub_ordernumber"));
            item.setCreatedDate(rs.getTimestamp("hub_createddate"));
            item.setModifiedDate(rs.getTimestamp("hub_modifieddate"));
            item.setCreatedBy(rs.getString("hub_createdby"));
            item.setModifiedBy(rs.getString("hub_modifiedby"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }
}

