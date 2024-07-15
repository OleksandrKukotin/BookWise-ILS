package org.geekhub.kukotin.coursework.service.documenttype;

public class DocumentType {

    private int typeId;
    private String typeName;

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
