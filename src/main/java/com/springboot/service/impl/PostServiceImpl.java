package com.springboot.service.impl;

import com.springboot.dtos.PostDto;
import com.springboot.entity.Post;
import com.springboot.repository.PostRepository;
import com.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert Dto to entity

        Post post=new Post();//created Post object

        post.setTitle(postDto.getTitle());//set all the dto details into post object
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);//saved postEntity into db using postRepository.save method

        //convert entity to dto

        PostDto postResponse=new PostDto();//created postDto object

        postResponse.setId(newPost.getId());//set all the newpost details to postDto
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }
}
