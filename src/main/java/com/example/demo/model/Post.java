package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @Column(nullable = false)
    private Date date = new Date();

    @Column(nullable = false)
    private String preview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Post(){}

    public Post(String title, String body, User author, Date date,String preview) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.date = date;
        this.preview = preview;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", preview='" + preview + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, author, date, preview);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.title, other.title)
                && Objects.equals(this.body, other.body)
                && Objects.equals(this.author, other.author)
                && Objects.equals(this.date, other.date)
                && Objects.equals(this.preview, other.preview);
    }
}
