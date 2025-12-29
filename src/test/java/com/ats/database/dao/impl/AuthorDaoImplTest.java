package com.ats.database.dao.impl;

import static com.ats.database.TestDataUtil.createTestAuthorA;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ats.database.domain.Author;

import java.util.List;

// unit test
// since unit test only checks individual components, all other services are mocked.
// no instance of spring boot is spun up.
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
    // mock jdbcTemplate
    @Mock
    private JdbcTemplate jdbcTemplate;

    // into this
    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void createTest() {
        Author author = createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
            eq("insert into authors values (?, ?, ?);"), 
            eq(1), 
            eq("abhijay"), 
            eq(22));
    }

    @Test
    public void readOneTest() {
        underTest.read(1);

        verify(jdbcTemplate).query(
            eq("select * from authors where id = ?;"),
            ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
            eq(1)
        );
    }

    @Test
    public void readManyTest() {
        List<Author> result = underTest.read();
        verify(jdbcTemplate).query(
                eq("select * from authors;"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void updateTest() {
        Author authorA = createTestAuthorA();
        underTest.update(2, "abhijayts", 22, 1);
        verify(jdbcTemplate).update(
                eq("update authors set author_id = ?, name = ?, age = ? where id = ?;"),
                eq(2),
                eq("abhijayts"),
                eq(22),
                eq(1)
        );
    }

    @Test
    public void deleteTest() {}
}
