package com.example.patserfelices.posts;

import com.example.patserfelices.comment.Comment;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Post {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String username;
    private String profilePicture;
    private String description;
    private String photo;
    private BigInteger createdAt;
    private Integer likes;
    @Transient
    private List<Comment> comments = null;

    public Post() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigInteger getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String toString() {
        return "Post{id=" + this.id + ", username='" + this.username + '\'' + ", profilePicture='" + this.profilePicture + '\'' + ", description='" + this.description + '\'' + ", photo='" + this.photo + '\'' + ", createdAt=" + this.createdAt + ", likes=" + this.likes + ", comments=" + this.comments + '}';
    }
}
