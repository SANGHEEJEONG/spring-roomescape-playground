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
        ReservationTime reservationTime = new ReservationTime(
                resultSet.getLong("id"),
                resultSet.getString("time")
        );

        return reservationTime;
    };

    public ReservationTime createReservationTime(ReservationTime reservationTime) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservationTime);
        Long newId = insertReservationTime.executeAndReturnKey(parameters).longValue();

        return new ReservationTime(newId, reservationTime.getTime());
    }

    public List<ReservationTime> findAllReservationTimes() {
        String sql = "select id, time from time";
        return jdbcTemplate.query(sql, reservationTimeRowMapper);
    }

    public ReservationTime findReservationTimeById(Long id) {
        String sql = "SELECT id, time FROM time WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, reservationTimeRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("예약 시간");
        }
    }

    public void deleteReservationTime(Long id) {
        String sql = "delete from time where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0) {
            throw new NotFoundException("예약 시간");
        }
    }
}
