package org.geekhub.kukotin.coursework.controller.document;

import org.springframework.lang.NonNull;

import java.time.Instant;

public record DocumentDTO(

    @NonNull Long documentId,
    @NonNull Long documentTypeId,
    @NonNull String documentTitle,
    String description,
    @NonNull Integer publishingYear,
    @NonNull Long authorId,
    @NonNull int countryId,
    @NonNull int availableCopies,
    @NonNull Instant updated,
    @NonNull Long updatedBy
) {
}
