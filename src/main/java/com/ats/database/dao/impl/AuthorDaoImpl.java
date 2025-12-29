package com.ats.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.ats.database.dao.AuthorDao;
import com.ats.database.domain.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("insert into authors values (?, ?, ?);", author.getId(), author.getName(), author.getAge());
    }

    // read one
    @Override
    public Optional<Author> read(int id) {
        List<Author> res = jdbcTemplate.query("select * from authors where id = ?;", new AuthorRowMapper(), id);
        return res.stream().findFirst();
    }

    // read many
    @Override
    public List<Author> read() {
        return jdbcTemplate.query("select * from authors;", new AuthorRowMapper());
    }

    @Override
    public void update(int id, String name, int age, int prevId) {
        jdbcTemplate.update("update authors set id = ?, name = ?, age = ? where id = ?;",
                id, name, age, prevId);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from authors where id = ?;", id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return Author.builder()
            .id(rs.getInt("id"))
            .name(rs.getString("name"))
            .age(rs.getInt("age"))
            .build();
        }
    }
}
