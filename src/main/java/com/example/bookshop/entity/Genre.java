package com.example.bookshop.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
@Data
public class Genre {
    private String name;
    @Getter
    @Id
    private Long id;
}
