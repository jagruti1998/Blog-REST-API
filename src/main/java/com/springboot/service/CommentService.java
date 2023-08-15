package com.springboot.service;

import com.springboot.dtos.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
}
