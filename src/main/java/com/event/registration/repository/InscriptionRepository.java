package com.event.registration.repository;

import com.event.registration.model.Inscription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class InscriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Inscription> ROW_MAPPER = (rs, rowNum) -> new Inscription(
            rs.getLong("id"),
            rs.getString("prenom"),
            rs.getString("nom"),
            rs.getString("telephone"),
            rs.getDate("date_naissance").toLocalDate(),
            rs.getString("sexe"),
            rs.getTimestamp("date_inscription").toLocalDateTime()
    );

    public InscriptionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Inscription save(Inscription inscription) {
        String sql = """
                INSERT INTO inscription (prenom, nom, telephone, date_naissance, sexe)
                VALUES (?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, inscription.getPrenom());
            ps.setString(2, inscription.getNom());
            ps.setString(3, inscription.getTelephone());
            ps.setDate(4, java.sql.Date.valueOf(inscription.getDateNaissance()));
            ps.setString(5, inscription.getSexe());
            return ps;
        }, keyHolder);

        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(id);
    }

    public Inscription findById(Long id) {
        String sql = "SELECT * FROM inscription WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, ROW_MAPPER, id);
    }

    public List<Inscription> findAll() {
        String sql = "SELECT * FROM inscription ORDER BY date_inscription DESC";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }
}
