package example.persistence.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Book {

    private Long id;
    private String Author;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
