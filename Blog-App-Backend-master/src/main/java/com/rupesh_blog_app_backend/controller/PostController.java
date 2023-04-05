package com.rupesh_blog_app_backend.controller;

import com.rupesh_blog_app_backend.payloads.ApiResponse;
import com.rupesh_blog_app_backend.payloads.PostDto;
import com.rupesh_blog_app_backend.payloads.PostResponse;
import com.rupesh_blog_app_backend.services.FileService;
import com.rupesh_blog_app_backend.services.PostService;
import com.rupesh_blog_app_backend.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable int userId,
                                              @PathVariable int categoryId) {
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable int postId) {
        PostDto createdPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable int userId,
                                                      @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                                      @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                                      @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                      @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_POST));

        PostResponse postDtoList = postService.getAllPostByUserId(userId, pageable);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<PostResponse> getPostBycategory(@PathVariable int categoryId,
                                                          @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                                          @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                                          @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                          @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_POST));

        PostResponse postResponse = postService.getAllPostByCategory(categoryId, pageable);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable int postId) {

        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                                   @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                                   @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                   @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_POST));

        PostResponse postResponse = postService.getAllPost(pageable);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted Succefully", true), HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<PostResponse> searchPost(@PathVariable String keyword,
                                                   @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                                   @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                                   @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                   @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_POST));

        PostResponse postResponse = postService.searchPost(keyword, pageable);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable int postId, @RequestParam("image")MultipartFile file) throws IOException {
        PostDto postDto=postService.getPostById(postId);

        String fileName1="post_"+postId+"_user_"+postDto.getUserEntity().getUserId();

        String fileName=fileService.uploadImage(path,file,fileName1);

        postDto.setImageName(fileName);
        PostDto updatedPostDto=postService.updatePost(postDto,postId);

        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("fileName") String fileName, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream=fileService.getResource(path,fileName);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,httpServletResponse.getOutputStream());
    }



}
