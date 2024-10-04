package org.geekhub.kukotin.coursework.service.entities.author;

import java.util.List;

public interface AuthorRepository {

    void save(Author author);

    Author findAuthorById(int authorId);

    List<Author> findAllAuthors(int pageNumber, int pageSize);

    void updateAuthor(Author author);

    void deleteAuthor(Author author);
}
