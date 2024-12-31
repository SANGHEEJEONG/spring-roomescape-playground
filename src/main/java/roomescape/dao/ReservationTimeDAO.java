package roomescape.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import roomescape.entity.ReservationTime;
import roomescape.exception.NotFoundException;
import roomescape.repository.ReservationTimeRepository;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ReservationTimeDAO implements ReservationTimeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertReservationTime;

    public ReservationTimeDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertReservationTime = new SimpleJdbcInsert(dataSource)
                .withTableName("time")   // 테이블 이름 설정
                .usingGeneratedKeyColumns("id"); // 자동 생성되는 키 컬럼 설정
    }

    private final RowMapper<ReservationTime> reservationTimeRowMapper = (resultSet, rowNum) -> {
        String timeStr = resultSet.getString("time");
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
        String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));

        ReservationTime reservationTime = new ReservationTime(
                resultSet.getLong("id"),
                formattedTime
        );

        return reservationTime;
    };

    public ReservationTime save(ReservationTime reservationTime) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservationTime);
        Long newId = insertReservationTime.executeAndReturnKey(parameters).longValue();

        return new ReservationTime(newId, reservationTime.getTime().toString());
    }

    public List<ReservationTime> findAll() {
        String sql = "select id, time from time";
        return jdbcTemplate.query(sql, reservationTimeRowMapper);
    }

    public ReservationTime findById(Long id) {
        String sql = "SELECT id, time FROM time WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, reservationTimeRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("예약 시간");
        }
    }

    public void delete(Long id) {
        String sql = "delete from time where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0) {
            throw new NotFoundException("예약 시간");
        }
    }
}
