package org.geekhub.kukotin.coursework.service.author;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public void createAuthor(Author author) {
        repository.save(author);
    }

    public Author getAuthorById(int id) {
        return repository.findAuthorById(id);
    }

    public List<Author> getAllAuthors(int pageNumber, int pageSize) {
        return repository.findAllAuthors(pageNumber, pageSize);
    }

    public void deleteAuthor(Author author) {
        repository.deleteAuthor(author);
    }

    public void updateAuthor(Author author) {
        repository.updateAuthor(author);
    }
}
