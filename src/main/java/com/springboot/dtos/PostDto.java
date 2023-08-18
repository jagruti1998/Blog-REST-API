package com.springboot.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.Set;

@Data //lombok annotations
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {
    private Long id;

    @Schema(description = "Blog Post title")
    //title should not be null or empty
    //title should have atleast two characters

    @NotEmpty
    @Size(min=2,message = "POst title should have atleast 2 chars")
    private String title;



    @Schema(description = "Blog Post description")
    //title should not be null or empty
    //title should have atleast 10 characters
    @NotEmpty
    @Size(min=10,message = "Post description should have 10 chars")
    private String description;



    @Schema(description = "Blog Post content")
    //title should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}