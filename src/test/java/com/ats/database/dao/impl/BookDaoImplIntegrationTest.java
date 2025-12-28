package com.ats.database.dao.impl;

import com.ats.database.TestDataUtil;
import com.ats.database.dao.AuthorDao;
import com.ats.database.dao.BookDao;
import com.ats.database.domain.Author;
import com.ats.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.ats.database.TestDataUtil;
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
    public void testBookCreationAndRead() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        // just this means no author
        // violates fk constraint
        // so author creation required
        Book book = TestDataUtil.createTestBook();
        book.setAuthor_id(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.read(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

}
