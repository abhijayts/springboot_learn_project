package com.ats.database.dao.impl;

import static com.ats.database.TestDataUtil.createTestBookA;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ats.database.domain.Book;

import java.util.List;

// unit test
// since unit test only checks individual components, all other services are mocked.
// no instance of spring boot is spun up.
@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void createTest() {
        Book book = createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("insert into books (isbn, title, author_id) values (?, ?, ?)"), 
                eq("1A"), 
                eq("my story"),
                eq(1));
    }

    @Test
    public void readOneTest() {
        underTest.read("1A");

        verify(jdbcTemplate).query(
            eq("select * from books where isbn = ?;"),
            ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
            eq("1A")
        );
    }

    @Test
    public void readManyTest() {
        List<Book> result = underTest.read();

        verify(jdbcTemplate).query(
                eq("select * from books;"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {
    }
}
