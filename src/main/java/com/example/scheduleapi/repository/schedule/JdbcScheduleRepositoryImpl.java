package com.example.scheduleapi.repository.schedule;

import com.example.scheduleapi.domain.Schedule;
import com.example.scheduleapi.dto.schedule.response.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class JdbcScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
    String query = "INSERT INTO SCHEDULES (CONTENT, USER_ID, PASSWORD) VALUES(?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getContent());
            ps.setLong(2, schedule.getUserId());
            ps.setString(3, schedule.getPassword());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        System.out.println("Generated key: " + id);
        return new ScheduleResponseDto(id, schedule.getContent(), null,null,null);
    }

    }

