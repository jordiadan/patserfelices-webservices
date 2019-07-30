package com.example.patserfelices.posts;

import com.example.patserfelices.comment.Comment;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String username;
    private String name;
    private String firstSurname;
    private String profilePicture;
    private String description;
    private String photo;
    private BigInteger createdAt;
    @ElementCollection
    private Set<String> likes = new HashSet<String>();
    private Long numberOfComments;

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

    public Set<String> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<String> likes) {
        this.likes = likes;
    }



    public String toString() {
        return "Post{id=" + this.id + ", username='" + this.username + '\'' + ", profilePicture='" + this.profilePicture + '\'' + ", description='" + this.description + '\'' + ", photo='" + this.photo + '\'' + ", createdAt=" + this.createdAt + ", likes=" + this.likes  + '}';
    }

    public Long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
