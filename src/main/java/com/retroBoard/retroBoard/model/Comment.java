package com.retroBoard.retroBoard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    private String body;
    private int likes;
    private boolean hasLiked;
    private int parentComment;

    public Comment(String body){
        this.body = body;
    }

    public Comment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public int getParentComment() {
        return parentComment;
    }

    public void setParentComment(int parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Comment))
            return false;
        Comment order = (Comment) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.body, order.body)
                && this.likes == order.likes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.body, this.likes);
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + this.id + ", body='" + this.body + '\'' + ", likes=" + this.likes + '}';
    }

}
