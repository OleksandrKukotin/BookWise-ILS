package org.geekhub.kukotin.coursework.service.entities.documenttype;

public class DocumentType {

    private final int typeId;
    private final String typeName;

    public DocumentType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }
}
