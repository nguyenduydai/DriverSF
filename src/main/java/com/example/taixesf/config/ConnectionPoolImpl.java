package com.example.taixesf.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool{
    private String driver;
    private String username;
    private String userpass;
    private String url;

    //doi tuong luu tru ket noi
    private Stack<Connection> pool;

    public ConnectionPoolImpl() {
        this.driver="com.mysql.jdbc.Driver";

        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.username="root";
        this.userpass="2003dai2003";
        this.url="jdbc:mysql://localhost:3306/taixesf?allowMultiQueries=true";
        this.pool=new Stack<Connection>();
    }

    public Connection getConnection() throws SQLException {
        if(this.pool.isEmpty()) {
            System.out.println("da khoi tao 1 ket noi");
            return DriverManager.getConnection(this.url,this.username,this.userpass);
        }else {
            System.out.println("da lay 1 ket noi moi");
            return this.pool.pop();
        }
    }
    public void releaseConnection(Connection con) throws SQLException {
        System.out.println("da tra ve 1 ket noi moi");
        this.pool.push(con);
    }
    public void finalized() throws Throwable{
        this.pool.clear();
        this.pool=null;
        System.out.println("da dong ket noi");
    }

}