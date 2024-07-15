package org.geekhub.kukotin.coursework.controller.documenttype;

import org.geekhub.kukotin.coursework.service.documenttype.DocumentType;

import java.util.List;

public class DocumentTypeConverter {

    private DocumentTypeConverter(){
    }

    public static DocumentTypeDTO toDto(DocumentType entity) {
        return new DocumentTypeDTO(entity.getTypeId(), entity.getTypeName());
    }

    public static DocumentType fromDto(DocumentTypeDTO dto) {
        return new DocumentType(dto.typeId(), dto.typeName());
    }

    public static List<DocumentTypeDTO> toDtos(List<DocumentType> entities) {
        return entities.stream()
            .map(DocumentTypeConverter::toDto)
            .toList();
    }
}
