package com.ats.database;

import com.ats.database.domain.Author;
import com.ats.database.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {}
    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1)
                .name("abhijay")
                .age(22)
                .build();
    }
    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2)
                .name("tilak")
                .age(22)
                .build();
    }
    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3)
                .name("singh")
                .age(22)
                .build();
    }
    public static Book createTestBook() {
        return Book.builder()
                .isbn("1A")
                .title("my story")
                .author_id(1)
                .build();
    }
}
