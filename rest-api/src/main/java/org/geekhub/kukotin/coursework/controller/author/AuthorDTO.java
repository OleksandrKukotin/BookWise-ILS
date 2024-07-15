package org.geekhub.kukotin.coursework.controller.author;

import org.springframework.lang.NonNull;

public record AuthorDTO(

    @NonNull int authorId,
    @NonNull String name,
    @NonNull String surname
) {
}
