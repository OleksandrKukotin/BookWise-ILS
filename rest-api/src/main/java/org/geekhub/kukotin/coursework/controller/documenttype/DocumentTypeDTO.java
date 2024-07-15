package org.geekhub.kukotin.coursework.controller.documenttype;

import org.springframework.lang.NonNull;

public record DocumentTypeDTO(

    int typeId,
    @NonNull String typeName
) {
}
