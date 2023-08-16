package com.springboot.dtos;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.Set;

@Data //lombok annotations
public class PostDto {
    private Long id;


    //title should not be null or empty
    //title should have atleast two characters

    @NotEmpty
    @Size(min=2,message = "POst title should have atleast 2 chars")
    private String title;

    //title should not be null or empty
    //title should have atleast 10 characters
    @NotEmpty
    @Size(min=10,message = "Post description should have 10 chars")
    private String description;

    //title should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}