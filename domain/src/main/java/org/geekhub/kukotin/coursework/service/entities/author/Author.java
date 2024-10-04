package org.geekhub.kukotin.coursework.service.entities.author;

public class Author {

    private final int authorId;
    private final String authorName;
    private final String authorSurname;

    public Author(int authorId, String authorName, String authorSurname) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }
}
