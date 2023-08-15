package com.springboot.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data //lombok annotations
public class PostDto {


    private Long id;
    private String title;
    private String description;
    private String content;
}