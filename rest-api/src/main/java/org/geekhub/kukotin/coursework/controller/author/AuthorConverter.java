package org.geekhub.kukotin.coursework.controller.author;

import org.geekhub.kukotin.coursework.service.entities.author.Author;

import java.util.List;

public class AuthorConverter {

    private AuthorConverter() {
    }

    public static AuthorDTO toDto(Author entity) {
        return new AuthorDTO(
            entity.getAuthorId(),
            entity.getAuthorName(),
            entity.getAuthorSurname());
    }

    public static List<AuthorDTO> toDtos(List<Author> entities) {
        return entities.stream()
            .map(AuthorConverter::toDto)
            .toList();
    }

    public static Author fromDto(AuthorDTO dto) {
        return new Author(
            dto.authorId(),
            dto.name(),
            dto.surname()
        );
    }
}
