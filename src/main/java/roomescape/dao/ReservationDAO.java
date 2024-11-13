package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.entity.Reservation;
import roomescape.exception.NotFoundReservationException;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReservationDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertReservation;

    public ReservationDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertReservation = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation")   // 테이블 이름 설정
                .usingGeneratedKeyColumns("id"); // 자동 생성되는 키 컬럼 설정
    }

    private final RowMapper<Reservation> reservationRowMapper = (resultSet, rowNum) -> {
        Reservation reservation = new Reservation(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("date"),
                resultSet.getString("time")
        );

        return reservation;
    };

    public List<Reservation> findAllReservations() {
        String sql = "select id, name, date, time from reservation";
        return jdbcTemplate.query(sql, reservationRowMapper);
    }

    public Reservation createReservation(Reservation reservation) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservation);
        Long newId = insertReservation.executeAndReturnKey(parameters).longValue();

        return new Reservation(newId, reservation.getName(), reservation.getDate().getDate(), reservation.getTime());
    }

    public void deleteReservation(Long id) {
        String sql = "delete from reservation where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0) {
            throw new NotFoundReservationException();
        }
    }
}
