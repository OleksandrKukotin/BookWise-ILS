package org.geekhub.kukotin.coursework.service.entities.document;

import java.time.Instant;

public record Document(int documentId, String documentTitle, int availableCopies,
                       Instant lastUpdatedTime, int updatedBy,
                       int publisherId, int informationId) {
}
