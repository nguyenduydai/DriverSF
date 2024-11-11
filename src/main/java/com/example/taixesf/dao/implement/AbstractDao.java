package com.example.taixesf.dao.implement;

import com.example.taixesf.config.ConnectionPool;
import com.example.taixesf.config.ConnectionPoolImpl;
import com.example.taixesf.mapper.RowMapper;
import com.example.taixesf.model.UserObject;
import com.example.taixesf.dao.GenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AbstractDao <T> implements GenericDao<T> {
    private Connection con;
    public void getConnection() {
        ConnectionPool cp=new ConnectionPoolImpl();
        try {
            this.con=cp.getConnection();
            if(this.con.getAutoCommit())	this.con.setAutoCommit(false);
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void setParameters(PreparedStatement pre, Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            try {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    pre.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    pre.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    pre.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    pre.setTimestamp(index, (Timestamp) parameter);
                } else {
                    pre.setNull(index, java.sql.Types.NULL);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean exe(PreparedStatement pre)
    {
        if(pre!=null) {
            try {
                int result=pre.executeUpdate();
                if(result==0) {
                    this.con.rollback();
                    return false;
                }
                this.con.commit();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    this.con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }finally {
                pre=null;
            }
        }
        return false;
    }
    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMappper, Object... parameters) {
        List<T> results=new ArrayList<>();
        this.getConnection();
        PreparedStatement pre=null;
        ResultSet result=null;
        try {
            pre=con.prepareStatement(sql);
            setParameters(pre,parameters);
            result=pre.executeQuery();
            while(result.next()) {
                results.add(rowMappper.mapRow(result));
            }
            return results;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(con!=null)	con.close();
                if(pre!=null)	pre.close();
                if(result!=null)result.close();
            }catch(SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    @Override
    public boolean update(String sql, Object... parameters) {
        this.getConnection();
        PreparedStatement pre=null;
        try {
            pre = con.prepareStatement(sql);
            setParameters(pre, parameters);
            this.exe(pre);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isExisting(UserObject item ) {
        boolean flag = false;
        PreparedStatement pre=null;
        this.getConnection();
        String sql = "SELECT user_id FROM tbluser WHERE user_phone = ?";
        try {
            pre=this.con.prepareStatement(sql);
            pre.setString(1, item.getUser_phone());
            ResultSet rs=pre.executeQuery();
            if(rs!=null) {
                while(rs.next()) {
                    System.out.println("user da ton tai");
                    flag=true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
