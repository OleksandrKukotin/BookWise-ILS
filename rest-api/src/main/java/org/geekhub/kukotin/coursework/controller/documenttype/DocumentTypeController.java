package org.geekhub.kukotin.coursework.controller.documenttype;

import org.geekhub.kukotin.coursework.service.documenttype.DocumentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.addDocumentType(DocumentTypeConverter.fromDto(documentTypeDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.updateDocumentType(DocumentTypeConverter.fromDto(documentTypeDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void deleteDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.deleteDocumentType(DocumentTypeConverter.fromDto(documentTypeDTO));
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeDTO>> getAllDocumentTypes() {
        return ResponseEntity.ok(DocumentTypeConverter.toDtos(documentTypeService.getAllDocumentTypes()));
    }
}
