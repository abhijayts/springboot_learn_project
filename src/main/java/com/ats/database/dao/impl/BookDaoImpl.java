package com.ats.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.ats.database.dao.BookDao;
import com.ats.database.domain.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Book> rowMapper = new BookRowMapper();

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("insert into books (isbn, title, author_id) values (?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthor_id());
    }

    @Override
    public Optional<Book> read(String isbn) {
        List<Book> res = jdbcTemplate.query("select * from books where isbn = ?;", rowMapper, isbn);
        return res.stream().findFirst();
    }

    @Override
    public List<Book> read() {
        return jdbcTemplate.query("select * from books;", rowMapper);
    }

    @Override
    public void update(String isbn, String title, int authorId, String prevIsbn) {
        jdbcTemplate.update(
                "update books set isbn = ?, title = ?, author_id = ? where isbn = ?;",
                isbn, title, authorId, prevIsbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("delete from books where isbn = ?;", isbn);
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            return Book.builder()
            .isbn(rs.getString("isbn"))
            .title(rs.getString("title"))
            .author_id(rs.getInt("author_id"))
            .build();
        }
    }
}
