package com.rupesh_blog_app_backend.repository;

import com.rupesh_blog_app_backend.entity.CommentEntity;
import com.rupesh_blog_app_backend.entity.PostEntity;
import com.rupesh_blog_app_backend.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommetRepository extends JpaRepository<CommentEntity, Integer> {

    Page<CommentEntity> findAllByPostEntity(PostEntity postEntity, Pageable pageable);
    Page<CommentEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

    Page<CommentEntity> findAllByPostEntityAndUserEntity(PostEntity postEntity, UserEntity userEntity, Pageable pageable);

}
