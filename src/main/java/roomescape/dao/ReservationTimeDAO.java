package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import roomescape.entity.ReservationTime;
import roomescape.repository.ReservationTimeRepository;

import javax.sql.DataSource;

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

    public ReservationTime createReservationTime(ReservationTime reservationTime) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(reservationTime);
        Long newId = insertReservationTime.executeAndReturnKey(parameters).longValue();

        return new ReservationTime(newId, reservationTime.getTime());
    }
}
