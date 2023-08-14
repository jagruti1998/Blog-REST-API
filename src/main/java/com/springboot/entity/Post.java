package com.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generates getters for all fields,useful to toString method, hashcode and also equivalent to getter setter method
@AllArgsConstructor //
@NoArgsConstructor


@Entity//jpa annotations to map entity as class
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}) //give name to the table and make field as unique
public class Post {
    @Id //specify primary key to our entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)//specify primary key generation strategy
    private Long id;
    @Column(name = "title",nullable = false)//column name and not null specification
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "content",nullable = false)
    private String content;
}