package org.geekhub.kukotin.coursework.service.documenttype;

import java.util.List;

public interface DocumentTypeRepository {

    void addType(DocumentType documentType);

    void updateType(DocumentType documentType);

    void deleteType(DocumentType documentType);

    List<DocumentType> getAllTypes();
}
