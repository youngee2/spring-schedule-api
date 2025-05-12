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
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<ScheduleResponseDto> findById(Long id) {
        String sql = """
                SELECT s.id,u.NAME, s.content, s.created_at,s.updated_at
                FROM SCHEDULES s JOIN USERS u
                ON s.user_id=u.user_id
                WHERE s.id= ?
                """;
        List<ScheduleResponseDto> result = jdbcTemplate.query(sql, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ), id
        );

        return result.stream().findFirst();


    }



}

