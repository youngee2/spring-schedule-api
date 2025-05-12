package com.example.api.schedule.repository;

import com.example.api.schedule.entity.Schedule;
import com.example.api.schedule.dto.response.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        return new ScheduleResponseDto(id, schedule.getContent(), null, null, null);
    }

    @Override
    public Optional<ScheduleResponseDto> findById(Long id) {
        String sql = """
                SELECT s.id,u.NAME, s.content, s.created_at,s.updated_at
                FROM SCHEDULES s JOIN USERS u
                ON s.user_id=u.user_id
                
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

    //조건 없이 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        String sql= """
                SELECT s.id,u.NAME, s.content, s.created_at,s.updated_at
                FROM SCHEDULES s JOIN USERS u
                ON s.user_id=u.user_id
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ));
    }

    //필터링된 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllFilterSchedules(String name, LocalDate updatedAt) {
        StringBuilder sql = new StringBuilder("""
                SELECT s.id,u.NAME, s.content, s.created_at,s.updated_at
                FROM SCHEDULES s JOIN USERS u
                ON s.user_id=u.user_id
                """);

        // 파라미터 순서대로 값 저장
        List<Object> params = new ArrayList<>();
        //조건 저장
        List<String> conditions = new ArrayList<>();

        //name이 null이 아니고 공백이 아닐 때 
        //name에 값이 있는 경우
        if (name != null && !name.isBlank()) {
            conditions.add("u.name = ?");
            params.add(name.trim()); // 공백 제거
        }

        //updateAt이 null이 아닐 때
        if (updatedAt != null) {
            LocalDateTime from = updatedAt.atStartOfDay();               // 00:00:00
            LocalDateTime to = updatedAt.atTime(LocalTime.MAX);          // 23:59:59.999999999
            conditions.add("s.updated_at BETWEEN ? AND ?");
            params.add(from);
            params.add(to);
        }

        // conditions 리스트가 비어있지 않은 경우.
        if (!conditions.isEmpty()) {
            sql.append("WHERE ");
            sql.append(String.join(" AND ", conditions));
            System.out.println(sql.toString());
        }

        return jdbcTemplate.query(sql.toString(),params.toArray(), (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                )
        );
    }
}