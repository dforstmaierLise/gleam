package com.gleam.backend.model;

import org.springframework.data.annotation.Id;

public class Review {
    @Id
    private String id;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }
}
