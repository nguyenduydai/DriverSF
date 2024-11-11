package com.example.taixesf.dao.implement;

import com.example.taixesf.mapper.UserMapper;
import com.example.taixesf.model.UserObject;
import com.example.taixesf.dao.UserFunction;

import java.util.ArrayList;
import java.util.List;

public class UserFunctionImpl extends AbstractDao<UserObject> implements UserFunction {
    @Override
    public UserObject getUser(int id) {
        String sql="SELECT * FROM tbluser WHERE user_id=?";
        List<UserObject> users= query(sql,new UserMapper(),id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public UserObject getUser(String userphone, String userpass) {
        String sql="SELECT * FROM tbluser WHERE (user_phone=?) and (user_password=md5(?))";
        List<UserObject> users= query(sql,new UserMapper(),userphone,userpass);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public ArrayList<UserObject> getUsers(UserObject similar, int at, byte total) {
        String sql="SELECT * FROM tbluser ORDER BY user_id ASC LIMIT "+at+", "+total;
        ArrayList<UserObject> listuser = (ArrayList<UserObject>) query(sql,new UserMapper());
        return listuser;
    }
    @Override
    public ArrayList<UserObject> getUsers(String similar) {
        String sql="SELECT * FROM tbluser WHERE "+similar;
        ArrayList<UserObject> listuser = (ArrayList<UserObject>) query(sql,new UserMapper());
        return listuser;
    }

    @Override
    public boolean addUser(UserObject item) {
        if(isExisting(item)){
            System.out.println("Them khong thanh cong người này đã tồn tại");
            return false;
        }
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO tbluser ");
        sql.append("(user_name, user_phone, user_password, user_email, user_age, user_sex, user_hometown, ");
        sql.append("user_image, user_status, user_salary, user_role_id, user_area_id, user_createddate, ");
        sql.append("user_createdby, user_modifieddate, user_modifiedby)");
        sql.append("VALUES (?,?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?)");
        return  update(sql.toString(), item.getUser_name(), item.getUser_phone(), item.getUser_password(),
                item.getUser_email(), item.getUser_age(), item.getUser_sex(), item.getUser_hometown(),
                item.getUser_image(), item.getUser_status(), item.getUser_salary(), item.getUser_role_id(),
                item.getUser_area_id(), item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(),
                item.getModifiedBy());
    }

    @Override
    public boolean editUser(UserObject item) {
        StringBuilder sql=new StringBuilder();
        sql.append("UPDATE tbluser ");
        sql.append("SET user_name=?, user_phone=?, user_password=md5(?), user_email=?, user_age=?, ");
        sql.append("user_sex=?, user_hometown=?, user_image=?, user_status=?, user_salary=?, user_role_id=?, user_area_id=?, ");
        sql.append("user_createddate=?, user_createdby=?, user_modifieddate=?, user_modifiedby=? WHERE user_id=?");
        return  update(sql.toString(), item.getUser_name(), item.getUser_phone(), item.getUser_password(),
                item.getUser_email(), item.getUser_age(), item.getUser_sex(), item.getUser_hometown(),
                item.getUser_image(), item.getUser_status(),item.getUser_salary(), item.getUser_role_id(),
                item.getUser_area_id(), item.getCreatedDate(), item.getCreatedBy(), item.getModifiedDate(),
                item.getModifiedBy(), item.getId());
    }

    @Override
    public boolean delUser(UserObject item) {
        String sql="DELETE FROM tbluser WHERE user_id=?";
        return update(sql,item.getId());
    }

}
