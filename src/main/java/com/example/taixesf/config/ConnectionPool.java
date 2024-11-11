package com.example.taixesf.config;
import java.sql.Connection;
import java.sql.SQLException;
public interface ConnectionPool {
    public Connection getConnection() throws SQLException;
    public void releaseConnection(Connection con) throws SQLException;
    public void finalized() throws Throwable;
}
