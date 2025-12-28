package com.ats.database.dao.impl;

import com.ats.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import  com.ats.database.TestDataUtil;
import static org.assertj.core.api.Assertions.assertThat;

// integration test
// since this is an integration test, actual spring boot context is spun up
// no mocking, so Autowired used for actual instances
@SpringBootTest
@Transactional
public class AuthorImplIntegrationTest {
    private final AuthorDaoImpl underTest;

    @Autowired
    public AuthorImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testOneAuthorCreationAndRead() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.read(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testManyAuthorCreationAndRead() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorA);
        underTest.create(authorB);
        underTest.create(authorC);
        List<Author> result = underTest.read();
        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);
    }

}
