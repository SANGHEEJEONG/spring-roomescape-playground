package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import roomescape.entity.Reservation;
import roomescape.entity.ReservationTime;
import roomescape.exception.NotFoundException;
import roomescape.repository.ReservationRepository;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ReservationDAO implements ReservationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertReservation;

    public ReservationDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertReservation = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation")   // 테이블 이름 설정
                .usingGeneratedKeyColumns("id"); // 자동 생성되는 키 컬럼 설정
    }

    private final RowMapper<Reservation> reservationRowMapper = (resultSet, rowNum) -> {
        ReservationTime reservationTime = new ReservationTime(resultSet.getLong("time_id"), resultSet.getString("time_value"));

        return new Reservation(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("date"),
                reservationTime
        );
    };


    public Reservation save(Reservation reservation) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", reservation.getName())
                .addValue("date", reservation.getDate())
                .addValue("time_id", reservation.getTime().getId());

        Long newId = insertReservation.executeAndReturnKey(parameters).longValue();

        return new Reservation(newId, reservation.getName(), reservation.getDate().getDate(), reservation.getTime());
    }

    public List<Reservation> findAll() {
        String sql = "SELECT \n" +
                "    r.id as reservation_id, \n" +
                "    r.name, \n" +
                "    r.date, \n" +
                "    t.id as time_id, \n" +
                "    t.time as time_value \n" +
                "FROM reservation as r inner join time as t on r.time_id = t.id";
        return jdbcTemplate.query(sql, reservationRowMapper);
    }

    public void delete(Long id) {
        String sql = "delete from reservation where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0) {
            throw new NotFoundException("예약");
        }
    }
}
