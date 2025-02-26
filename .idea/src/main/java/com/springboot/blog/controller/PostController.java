package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostSevice;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Tag(
        name = "CRUD REST APIs for POST RESOURCE"
)
public class PostController {

    private PostSevice postSevice;

    public PostController(PostSevice postSevice) {
        this.postSevice = postSevice;
    }

    @Operation(
            summary = "Creat Post Rest API",
            description = "Save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @SecurityRequirement(
            name = "Bear Authentication"

    )
    // create blog post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postSevice.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postSevice.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }


    @Operation(
            summary = "Get Post By Id Rest API",
            description = "Get posts from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 success"
    )
    // get posts by id
    @GetMapping(value = "/api/v1/posts/{id}")  //("/api/v1/posts/{id}")
    public ResponseEntity <PostDto> getPostByIdV1(@PathVariable long id){
        return ResponseEntity.ok(postSevice.getPostById(id));
    }

    @Operation(
            summary = "Update Post Rest API",
            description = "Update post into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // updata post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> update(@Valid @RequestBody PostDto postDto, @PathVariable long id){
        return new ResponseEntity<>(postSevice.updatePost(postDto, id), HttpStatus.OK);
    }



    @Operation(
            summary = "Detele Post Rest API",
            description = "Delete post from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    // delete post by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
         postSevice.deletePostById(id);
         return new ResponseEntity<>("Post deleted successfully.", HttpStatus.OK);
    }


    // get posts by categoryId
    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(postSevice.getPostsByCategoryId(categoryId));
    }
}
