package org.geekhub.kukotin.coursework.service.author;

public class Author {

    private int authorId;
    private String authorName;
    private String authorSurname;

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
