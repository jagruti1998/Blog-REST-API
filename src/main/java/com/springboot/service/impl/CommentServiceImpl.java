package com.springboot.service.impl;

import com.springboot.dtos.CommentDto;
import com.springboot.entity.Comment;
import com.springboot.entity.Post;
import com.springboot.exception.BlogAPIException;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.repository.CommentRepository;
import com.springboot.repository.PostRepository;
import com.springboot.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;

    private PostRepository postRepository;



    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment=mapToEntity(commentDto);

        //retrieve post entity by id
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id",postId));


        //set post to comment entity
        comment.setPost(post);

        //save comment enity in db

        Comment newComment=commentRepository.save(comment);

        return mapToDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {

        //retrieve list of comments by postId

        List<Comment> comments=commentRepository.findByPostId(postId);
        //convert list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        //retrieve post entity by id
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id",postId));

        //retrieve comment by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if(comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {
        //retrieve post entity by id
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id",postId));

        //retrieve comment by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }


        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment= commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;

    }


    private Comment mapToEntity(CommentDto commentDto){
        Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}
