package com.inori.skywalking.springbootwar.service.impl;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.JDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JDBCServiceImpl implements JDBCService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(String id) {
        String sql = "select * from user t where t.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getUsersInfo() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }
}
