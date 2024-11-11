package com.example.taixesf.dao.implement;

import com.example.taixesf.dao.RoleFunction;
import com.example.taixesf.dao.UserFunction;
import com.example.taixesf.mapper.RoleMapper;
import com.example.taixesf.mapper.UserMapper;
import com.example.taixesf.model.RoleObject;
import com.example.taixesf.model.UserObject;

import java.util.ArrayList;
import java.util.List;

public class RoleFunctionImpl  extends AbstractDao<RoleObject> implements RoleFunction {
    @Override
    public RoleObject getRole(int id) {
        String sql="SELECT * FROM tblrole WHERE role_id=?";
        List<RoleObject> roles= query(sql,new RoleMapper(),id);
        return roles.isEmpty() ? null : roles.get(0);
    }
    public RoleObject getRole(String name) {
        String sql="SELECT * FROM tblrole WHERE role_name=?";
        List<RoleObject> roles= query(sql,new RoleMapper(),name);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public ArrayList<RoleObject> getRoles(RoleObject similar, int at, byte total) {
        String sql="SELECT * FROM tblrole ORDER BY role_id DESC LIMIT "+at+", "+total;
        ArrayList<RoleObject> listrole = (ArrayList<RoleObject>) query(sql,new RoleMapper());
        return listrole;
    }

    @Override
    public boolean addRole(RoleObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO tblrole ");
        sql.append("(role_name, role_posititon, role_createddate, ");
        sql.append("role_createdby, role_modifieddate, role_modifiedby)");
        sql.append("VALUES (?,?,?,?,?,?)");
        return  update(sql.toString(), item.getRole_name(), item.getRole_position(),
                item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy());
    }

    @Override
    public boolean editRole(RoleObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("UPDATE tblrole SET role_name=?, role_position=?, ");
        sql.append("role_createddate=?, role_createdby=?, role_modifieddate=?, role_modifiedby=? WHERE role_id=?");
        return  update(sql.toString(), item.getRole_name(), item.getRole_position(), item.getCreatedDate(),
                item.getCreatedBy(), item.getModifiedDate(), item.getModifiedBy(),item.getId());
    }

    @Override
    public boolean delRole(RoleObject item) {
        String sql="DELETE FROM tblrole WHERE role_id=?";
        return update(sql,item.getId());
    }
}
