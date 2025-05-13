package com.example.api.user.repository;

import com.example.api.schedule.dto.response.ScheduleResponseDto;
import com.example.api.user.dto.response.UserSaveResponseDto;
import com.example.api.user.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;


    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserSaveResponseDto save(User user) {
        String sql = "INSERT INTO users (name, user_email, created_at, updated_at) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUserEmail());
            ps.setObject(3, user.getCreatedAt().now());
            ps.setObject(4, user.getUpdatedAt().now());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new UserSaveResponseDto(id, user.getName(), user.getUserEmail(), user.getCreatedAt().now(), user.getUpdatedAt().now());
    }
}
