package com.rupesh_blog_app_backend.services.impl;

import com.rupesh_blog_app_backend.exeptions.ResourceNotFountException;
import com.rupesh_blog_app_backend.payloads.CommentDto;
import com.rupesh_blog_app_backend.payloads.CommentResponse;
import com.rupesh_blog_app_backend.entity.CommentEntity;
import com.rupesh_blog_app_backend.entity.PostEntity;
import com.rupesh_blog_app_backend.entity.UserEntity;
import com.rupesh_blog_app_backend.repository.CommetRepository;
import com.rupesh_blog_app_backend.repository.PostRepository;
import com.rupesh_blog_app_backend.repository.UserRepository;
import com.rupesh_blog_app_backend.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommetRepository commetRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId, int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User", "id", userId));
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFountException("Post", "id", postId));

        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
        commentEntity.setUserEntity(userEntity);
        commentEntity.setPostEntity(postEntity);

        CommentEntity createdComment = commetRepository.save(commentEntity);

        return modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        CommentEntity commentEntity = commetRepository.findById(commentId).orElseThrow(() -> new ResourceNotFountException("Comment", "id", commentId));
        commetRepository.delete(commentEntity);
    }

    @Override
    public CommentDto getCommentById(int commentId) {
        CommentEntity commentEntity = commetRepository.findById(commentId).orElseThrow(() -> new ResourceNotFountException("Comment", "id", commentId));

        return modelMapper.map(commentEntity, CommentDto.class);
    }

    @Override
    public CommentResponse getAllComment(Pageable pageable) {
        Page<CommentEntity> commentEntityPage = commetRepository.findAll(pageable);

        List<CommentEntity> commentEntityList = commentEntityPage.getContent();

        List<CommentDto> commentDtoList = commentEntityList.stream().map(commentEntity -> modelMapper.map(commentEntity, CommentDto.class)).collect(Collectors.toList());
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentDtoList);
        commentResponse.setPageNumber(commentEntityPage.getNumber());
        commentResponse.setPageSize(commentEntityPage.getSize());
        commentResponse.setTotalElement(commentEntityPage.getTotalElements());
        commentResponse.setTotalPages(commentEntityPage.getTotalPages());
        commentResponse.setLastPage(commentEntityPage.isLast());


        return commentResponse;
    }

    @Override
    public CommentResponse getCommentByPostId(int postId, Pageable pageable) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFountException("Post", "id", postId));
        Page<CommentEntity> commentEntityPage = commetRepository.findAllByPostEntity(postEntity, pageable);


        List<CommentEntity> commentEntityList = commentEntityPage.getContent();

        List<CommentDto> commentDtoList = commentEntityList.stream().map(commentEntity -> modelMapper.map(commentEntity, CommentDto.class)).collect(Collectors.toList());
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentDtoList);
        commentResponse.setPageNumber(commentEntityPage.getNumber());
        commentResponse.setPageSize(commentEntityPage.getSize());
        commentResponse.setTotalElement(commentEntityPage.getTotalElements());
        commentResponse.setTotalPages(commentEntityPage.getTotalPages());
        commentResponse.setLastPage(commentEntityPage.isLast());


        return commentResponse;
    }

    @Override
    public CommentResponse getCommentByUserId(int userId, Pageable pageable) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User", "id", userId));
        Page<CommentEntity> commentEntityPage = commetRepository.findAllByUserEntity(userEntity, pageable);


        List<CommentEntity> commentEntityList = commentEntityPage.getContent();

        List<CommentDto> commentDtoList = commentEntityList.stream().map(commentEntity -> modelMapper.map(commentEntity, CommentDto.class)).collect(Collectors.toList());
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentDtoList);
        commentResponse.setPageNumber(commentEntityPage.getNumber());
        commentResponse.setPageSize(commentEntityPage.getSize());
        commentResponse.setTotalElement(commentEntityPage.getTotalElements());
        commentResponse.setTotalPages(commentEntityPage.getTotalPages());
        commentResponse.setLastPage(commentEntityPage.isLast());


        return commentResponse;
    }

    @Override
    public CommentResponse getCommentByPostIdAndUserID(int postId, int userId, Pageable pageable) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFountException("Post", "id", postId));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User", "id", userId));

        Page<CommentEntity> commentEntityPage = commetRepository.findAllByPostEntityAndUserEntity(postEntity, userEntity, pageable);


        List<CommentEntity> commentEntityList = commentEntityPage.getContent();

        List<CommentDto> commentDtoList = commentEntityList.stream().map(commentEntity -> modelMapper.map(commentEntity, CommentDto.class)).collect(Collectors.toList());
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentDtoList);
        commentResponse.setPageNumber(commentEntityPage.getNumber());
        commentResponse.setPageSize(commentEntityPage.getSize());
        commentResponse.setTotalElement(commentEntityPage.getTotalElements());
        commentResponse.setTotalPages(commentEntityPage.getTotalPages());
        commentResponse.setLastPage(commentEntityPage.isLast());


        return commentResponse;
    }
}
