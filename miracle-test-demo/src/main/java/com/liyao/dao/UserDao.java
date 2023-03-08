package com.liyao.dao;

import com.liyao.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author 栗垚
 * @Date 2023/3/1
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserById(long id) {
        return jdbcTemplate.queryForObject("SELECT id, name FROM t_user WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class), id);
    }
}
