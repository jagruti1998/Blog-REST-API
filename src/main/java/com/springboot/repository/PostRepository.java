package com.springboot.repository;

import com.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//Created PostRepository interface which extends JpaRepository and we provided entity type as a post and datatype as Long
public interface PostRepository extends JpaRepository<Post,Long> {
}
//No need to write any code here because Spring Data JPA has a JpaRepository interface.
//It internally provides all the CRUD database operation methods and its implementation for us.
//internally provides methods like findAll, findAllById, saveAll,


//NOTE: We don't need to add @Repository annotation to this interface because the JpaRepository interface has an implementation
//class called SimpleJpaRepository and it internally annotated with @Repository annotation and @transactional annotation