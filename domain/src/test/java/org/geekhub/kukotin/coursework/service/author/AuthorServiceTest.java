package org.geekhub.kukotin.coursework.service.author;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void createAuthor_ValidAuthor_Success() {
        Author author = new Author(1, "John", "Doe");

        authorService.createAuthor(author);

        verify(authorRepository).save(author);
    }

    @Test
    void getAuthorById_ExistingId_ReturnsAuthor() {
        Author expectedAuthor = new Author(1, "Jane", "Smith");

        when(authorRepository.findAuthorById(1)).thenReturn(expectedAuthor);

        Author actualAuthor = authorService.getAuthorById(1);

        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    void getAllAuthors_ValidPageNumberAndPageSize_ReturnsAuthors() {
        int pageNumber = 1;
        int pageSize = 10;
        List<Author> expectedAuthors = Arrays.asList(
            new Author(1, "John", "Doe"),
            new Author(2, "Jane", "Smith")
        );

        when(authorRepository.findAllAuthors(pageNumber, pageSize)).thenReturn(expectedAuthors);

        List<Author> actualAuthors = authorService.getAllAuthors(pageNumber, pageSize);

        assertEquals(expectedAuthors, actualAuthors);
    }

    @Test
    void deleteAuthor_ValidAuthor_Success() {
        Author author = new Author(1, "John", "Doe");

        authorService.deleteAuthor(author);

        verify(authorRepository).deleteAuthor(author);
    }

    @Test
    void updateAuthor_ValidAuthor_Success() {
        Author author = new Author(1, "John", "Doe");

        authorService.updateAuthor(author);

        verify(authorRepository).updateAuthor(author);
    }
}
