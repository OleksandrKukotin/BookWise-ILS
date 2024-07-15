package org.geekhub.kukotin.coursework.controller.author;

import org.geekhub.kukotin.coursework.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createAuthor(@RequestBody AuthorDTO dto) {
        authorService.createAuthor(AuthorConverter.fromDto(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        return new ResponseEntity<>(AuthorConverter.toDto(authorService.getAuthorById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return new ResponseEntity<>(AuthorConverter.toDtos(authorService.getAllAuthors(1,10)), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void updateAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.updateAuthor(AuthorConverter.fromDto(authorDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping()
    public void deleteAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.deleteAuthor(AuthorConverter.fromDto(authorDTO));
    }
}
