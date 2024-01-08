package com.example.blog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime creationDate;
}
