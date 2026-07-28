package com.event.registration.repository;

import com.event.registration.model.Evenement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EvenementRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Evenement> ROW_MAPPER = (rs, rowNum) -> new Evenement(
            rs.getLong("id"),
            rs.getString("nom"),
            rs.getString("description"),
            rs.getDate("date_evenement").toLocalDate(),
            rs.getString("lieu")
    );

    public EvenementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Evenement> findAll() {
        String sql = "SELECT * FROM evenement ORDER BY date_evenement ASC";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    public Optional<Evenement> findById(Long id) {
        String sql = "SELECT * FROM evenement WHERE id = ?";
        List<Evenement> results = jdbcTemplate.query(sql, ROW_MAPPER, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }
}
