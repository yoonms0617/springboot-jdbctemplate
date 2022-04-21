package com.example.springjdbc.repository;

import com.example.springjdbc.model.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository {

    private static final String USER_FIND_BY_ID = "SELECT * FROM USER WHERE user_id = ?";
    private static final String USER_FIND_BY_NAME = "SELECT * FROM USER WHERE user_name = ?";
    private static final String USER_FIND_BY_EMAIL = "SELECT * FROM USER WHERE user_email = ?";
    private static final String USER_FIND_ALL = "SELECT * FROM USER";

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("USER").usingGeneratedKeyColumns("user_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_name", user.getName())
                .addValue("user_email", user.getEmail());
        long id = jdbcInsert.executeAndReturnKey(params).longValue();
        user.setId(id);
        return id;
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(USER_FIND_BY_ID, getUserRowMapper(), id);
    }

    @Override
    public User findByName(String name) {
        return jdbcTemplate.queryForObject(USER_FIND_BY_NAME, getUserRowMapper(), name);
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(USER_FIND_BY_EMAIL, getUserRowMapper(), email);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(USER_FIND_ALL, getUserRowMapper());
    }

    private RowMapper<User> getUserRowMapper() {
        return (rs, rowNum) -> User.builder()
                .name(rs.getString("user_name"))
                .email(rs.getString("user_email"))
                .build();
    }

}
