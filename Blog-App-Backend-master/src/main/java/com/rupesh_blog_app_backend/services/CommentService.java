package com.rupesh_blog_app_backend.services;

import com.rupesh_blog_app_backend.payloads.CommentDto;
import com.rupesh_blog_app_backend.payloads.CommentResponse;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int postId, int userId);

    void deleteComment(int commentId);

    CommentDto getCommentById(int commentId);

    CommentResponse getAllComment(Pageable pageable);

    CommentResponse getCommentByPostId(int postId,Pageable pageable);
    CommentResponse getCommentByUserId(int userId,Pageable pageable);

    CommentResponse getCommentByPostIdAndUserID(int postId,int userId,Pageable pageable);

}
