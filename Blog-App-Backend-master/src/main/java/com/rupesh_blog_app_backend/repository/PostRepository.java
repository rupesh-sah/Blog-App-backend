package com.rupesh_blog_app_backend.repository;

import com.rupesh_blog_app_backend.entity.CategoryEntity;
import com.rupesh_blog_app_backend.entity.PostEntity;
import com.rupesh_blog_app_backend.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {

    Page<PostEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);
    Page<PostEntity> findAllByCategoryEntity(CategoryEntity categoryEntity, Pageable pageable);

    Page<PostEntity> findAllByTitleContaining(String title,Pageable pageable);


}
