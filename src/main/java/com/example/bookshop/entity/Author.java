package com.example.bookshop.entity;
import lombok.Data;
import javax.persistence.*;
@Entity
@Table(name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
