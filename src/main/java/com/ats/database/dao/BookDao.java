package com.ats.database.dao;

import java.util.List;
import java.util.Optional;
import com.ats.database.domain.Book;

public interface BookDao{
    void create(Book book);
    Optional<Book> read(String isbn);
    List<Book> read();
    void update(String isbn, String title, int authorId, String prevIsbn);
    void delete(String isbn);
}