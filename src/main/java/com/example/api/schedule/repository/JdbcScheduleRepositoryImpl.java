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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //일정 저장
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
        return new ScheduleResponseDto(id, schedule.getContent(), null, null, null);
    }


    @Override
    public Optional<ScheduleResponseDto> findById(Long id) {
        String sql = """
                SELECT s.id,u.NAME, s.content, s.created_at,s.updated_at
                FROM SCHEDULES s JOIN USERS u
                ON s.user_id=u.user_id
                WHERE s.id=?
                """;
        List<ScheduleResponseDto> result = jdbcTemplate.query(sql,  new Object[]{id}, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                )
        );
        return result.stream().findFirst();
    }





    //필터링된 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllFilterSchedules(Map<String,Object> result) {
        StringBuilder sql = new StringBuilder("""
                SELECT s.id,u.NAME, s.content, s.created_at,s.updated_at
                FROM SCHEDULES s JOIN USERS u
                ON s.user_id=u.user_id
                """);

        List<Object> params = Optional.ofNullable((List<Object>) result.get("Params")).orElse(new ArrayList<>());
        List<String> conditions = (List<String>) result.get("conditions");

        // 파라미터 순서대로 값 저장
        // conditions 리스트가 비어있지 않은 경우.
        if (!conditions.isEmpty()) {
            sql.append("WHERE ");
            sql.append(String.join(" AND ",conditions));
        }
        sql.append(" ORDER BY s.updated_at DESC");

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


    //일정 삭제
    @Override
    public int deleteSchedule(Long id, String password) {
        return  jdbcTemplate.update("delete from schedules WHERE id=? AND password=?", id,password);
    }


    //일정 수정
    @Override
    public Optional<ScheduleResponseDto> updateSchedule(Long id, String userName, String content, String password, LocalDateTime updateAt) {
        String sql= """
                UPDATE schedules
                SET content = ?, updated_at = ?, user_id= (SELECT user_id FROM users WHERE name= ?)
                WHERE id=? AND password=?
                """;

        jdbcTemplate.update(sql,content,LocalDateTime.now(), userName,id, password);
        return findById(id);
    }
}