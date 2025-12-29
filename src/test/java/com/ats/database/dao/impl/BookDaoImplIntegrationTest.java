package com.ats.database.dao.impl;

import com.ats.database.TestDataUtil;
import com.ats.database.dao.AuthorDao;
import com.ats.database.domain.Author;
import com.ats.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class BookDaoImplIntegrationTest {

    private final AuthorDao authorDao;
    private final BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(AuthorDao authorDao, BookDaoImpl underTest) {
        this.authorDao = authorDao;
        this.underTest = underTest;
    }

    @Test
    public void testOneBookCreationAndRead() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        // just this means no author
        // violates fk constraint
        // so author creation required
        Book book = TestDataUtil.createTestBookA();
        book.setAuthor_id(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.read(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testManyBookCreationAndRead() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();
        authorDao.create(authorA);
        authorDao.create(authorB);
        authorDao.create(authorC);
        // we did above to avoid fk constraint
        Book bookA = TestDataUtil.createTestBookA();
        Book bookB = TestDataUtil.createTestBookB();
        Book bookC = TestDataUtil.createTestBookC();
        Book bookD = TestDataUtil.createTestBookD();
        underTest.create(bookA);
        underTest.create(bookB);
        underTest.create(bookC);
        underTest.create(bookD);
        List<Book> result = underTest.read();
        assertThat(result).hasSize(4).containsExactly(bookA, bookB, bookC, bookD);
    }

    @Test
    public void testBookUpdate() {
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);
        Book bookA = TestDataUtil.createTestBookA();
        underTest.create(bookA);
        underTest.update("2A", "my story", 1, "1A");
        bookA.setIsbn("2A");
        Optional<Book> result = underTest.read(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

}
