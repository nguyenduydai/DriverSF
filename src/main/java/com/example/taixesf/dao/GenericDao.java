package com.example.taixesf.dao;

import com.example.taixesf.mapper.RowMapper;

import java.util.List;

public interface GenericDao <T>{
    <T> List<T> query(String sql, RowMapper<T> rowMappper, Object... parameters);
    boolean update(String sql,Object... parameters);

}
