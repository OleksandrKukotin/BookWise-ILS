package org.geekhub.kukotin.coursework.service.documenttype;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public void addDocumentType(DocumentType documentType) {
        documentTypeRepository.addType(documentType);
    }

    public void updateDocumentType(DocumentType documentType) {
        documentTypeRepository.updateType(documentType);
    }

    public void deleteDocumentType(DocumentType documentType) {
        documentTypeRepository.deleteType(documentType);
    }

    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.getAllTypes();
    }
}
