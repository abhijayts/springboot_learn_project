package com.ats.database.dao;

import java.util.Optional;
import com.ats.database.domain.Book;

public interface BookDao{
    void create(Book book);
    Optional<Book> read(String isbn);
    void update();
    void delete();
}