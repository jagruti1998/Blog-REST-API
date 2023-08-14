package com.springboot.service.impl;

import com.springboot.dtos.PostDto;
import com.springboot.entity.Post;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.repository.PostRepository;
import com.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert Dto to entity

        Post post=mapToEntity(postDto);
        Post newPost=postRepository.save(post);
/*
        Post post=new Post();//created Post object
        post.setTitle(postDto.getTitle());//set all the dto details into post object
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);//saved postEntity into db using postRepository.save method
*/

        //convert entity to dto

        PostDto postResponse=mapToDto(newPost);//created postDto object
        return postResponse;
    }
/*
        postResponse.setId(newPost.getId());//set all the newpost details to postDto
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
*/


    @Override
    public List<PostDto> getAllPosts() {

        // Retrieve a list of Post entities from the database
        List<Post> posts=postRepository.findAll();//postRepository method return list of posts

        // Use the Stream API to transform each Post entity into a PostDto
        // and collect them into a list of PostDto
        return posts.stream().map(post -> mapToDto(post))// Transform each Post to PostDto
                .collect(Collectors.toList());//Collect into a List<PostDto>
    }

    //
    @Override
    public PostDto getPostById(long id) {//retrive post entity by id
        Post post= postRepository.findById(id)//declare post object call postRepository, which provides findById() method
                .orElseThrow(() -> new ResourceNotFoundException("Post","id",id));//if object does not exist to given id we use throw exception
      //  pass lambda expression and throw ResourceNotFoundException and pass parameters

        return mapToDto(post);//convert post entity to dto
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id from the db
        Post post= postRepository.findById(id)//declare post object call postRepository, which provides findById() method
                .orElseThrow(() -> new ResourceNotFoundException("Post","id",id));//if object does not exist to given id we use throw exception

        post.setTitle(postDto.getTitle());//set all the updated values to post entity
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost=postRepository.save((post));//save the updated post into db
        return mapToDto(updatePost);//convert updated post to dto
    }

    //converted entity into Dto
    private PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }


    //convert Dto to entity

    private Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
