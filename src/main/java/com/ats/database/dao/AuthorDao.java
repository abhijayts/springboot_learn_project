package com.ats.database.dao;

import java.util.List;
import java.util.Optional;
import com.ats.database.domain.Author;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> read(int id); //read one
    List<Author> read(); // read many
    void update();
    void delete();
}
