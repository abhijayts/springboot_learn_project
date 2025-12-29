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
    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1A")
                .title("my story")
                .author_id(1)
                .build();
    }
    public static Book createTestBookB() {
        return Book.builder()
                .isbn("2B")
                .title("my life story")
                .author_id(2)
                .build();
    }
    public static Book createTestBookC() {
        return Book.builder()
                .isbn("3C")
                .title("my fing story")
                .author_id(1)
                .build();
    }
    public static Book createTestBookD() {
        return Book.builder()
                .isbn("4D")
                .title("my love story")
                .author_id(3)
                .build();
    }
}
