package com.rupesh_blog_app_backend.controller;

import com.rupesh_blog_app_backend.payloads.ApiResponse;
import com.rupesh_blog_app_backend.payloads.CommentDto;
import com.rupesh_blog_app_backend.payloads.CommentResponse;
import com.rupesh_blog_app_backend.services.CommentService;
import com.rupesh_blog_app_backend.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/comment/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createCooment(@Valid @RequestBody CommentDto commentDto,
                                                    @PathVariable int userId, @PathVariable int postId) {
        CommentDto createdComment = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Successfully Deleted", true), HttpStatus.OK);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable int commentId) {
        CommentDto createdComment = commentService.getCommentById(commentId);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @GetMapping("/comment")
    public ResponseEntity<CommentResponse> getAllComment(@RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                                         @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                                         @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                         @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_COMMENT));

        CommentResponse commentResponse = commentService.getAllComment(pageable);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @GetMapping("/comment/post/{postId}")
    public ResponseEntity<CommentResponse> getCommentByPostId(@PathVariable int postId,
                                              @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                              @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                              @RequestParam(value = "sortBy") Optional<String> sortBy,
                                              @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_COMMENT));


        CommentResponse commentResponse = commentService.getCommentByPostId(postId, pageable);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @GetMapping("/comment/user/{userId}")
    public ResponseEntity<CommentResponse> getCommentByUserId(@PathVariable int userId,
                                              @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                              @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                              @RequestParam(value = "sortBy") Optional<String> sortBy,
                                              @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_COMMENT));


        CommentResponse commentResponse = commentService.getCommentByUserId(userId, pageable);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @GetMapping("/comment/post/{postId}/user/{userId}")
    public ResponseEntity<CommentResponse> getCommentByPostIdAndUserID(@PathVariable int userId,
                                                                  @PathVariable int postId,
                                                                  @RequestParam(value = "pageSize") Optional<Integer> pageSize,
                                                                  @RequestParam(value = "pageNumber") Optional<Integer> pageNumber,
                                                                  @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                                  @RequestParam(value = "sortDir") Optional<Sort.Direction> sort) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(AppConstants.PAGE_NUMBBER),
                pageSize.orElse(AppConstants.PAGE_SIZE), sort.orElse(AppConstants.sort), sortBy.orElse(AppConstants.SORT_BY_FOR_COMMENT));



        CommentResponse commentResponse = commentService.getCommentByPostIdAndUserID(postId, userId, pageable);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

}
