package com.example.demo.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PostDto {

    @NotEmpty
    private String title;
    @NotEmpty
    private String body;

    public PostDto(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public PostDto() {
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

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
