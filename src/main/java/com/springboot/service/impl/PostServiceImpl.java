package com.springboot.service.impl;

import com.springboot.dtos.PostDto;
import com.springboot.dtos.PostResponse;
import com.springboot.entity.Post;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.repository.PostRepository;
import com.springboot.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;


    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {//injected modelmapper in class
        this.postRepository = postRepository;
        this.mapper=mapper;
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
    public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        // Retrieve a list of Post entities from the database
        Page<Post> posts=postRepository.findAll(pageable);//postRepository method return list of posts

        //get content for page object
        List<Post> listOfPosts=posts.getContent();

        // Use the Stream API to transform each Post entity into a PostDto
        // and collect them into a list of PostDto
        List<PostDto> content= listOfPosts.stream().map(post -> mapToDto(post))// Transform each Post to PostDto
                .collect(Collectors.toList());//Collect into a List<PostDto>

        PostResponse postResponse=new PostResponse();//created postResponse object

        postResponse.setContent(content);//set all the required details
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
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
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }


    @Override
    public void deletePostById(long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // convert Entity into DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }


    //converted entity into Dto
    private PostDto mapToDto(Post post){
        PostDto postDto=mapper.map(post,PostDto.class);

  /*      PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());   */

        return postDto;
    }


    //convert Dto to entity

    private Post mapToEntity(PostDto postDto){
        Post post=mapper.map(postDto,Post.class);

/*        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());*/
        
        return post;
    }
}