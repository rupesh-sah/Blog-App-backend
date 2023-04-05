package com.rupesh_blog_app_backend.services.impl;

import com.rupesh_blog_app_backend.exeptions.ResourceNotFountException;
import com.rupesh_blog_app_backend.payloads.CategoryDto;
import com.rupesh_blog_app_backend.entity.CategoryEntity;
import com.rupesh_blog_app_backend.repository.CategoryRepository;
import com.rupesh_blog_app_backend.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity=categoryRepository.save(convertToCategoryEntity(categoryDto));
        return convertToCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        CategoryEntity categoryEntity=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFountException("Caregory","id",categoryId));

        categoryEntity.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryEntity.setCategoryDescription(categoryDto.getCategoryDescription());
        CategoryEntity updatedCategoey=categoryRepository.save(categoryEntity);

        return convertToCategoryDto(updatedCategoey);
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {
        CategoryEntity categoryEntity=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFountException("Caregory","id",categoryId));
        return convertToCategoryDto(categoryEntity);
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        CategoryEntity categoryEntity=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFountException("Caregory","id",categoryId));
        categoryRepository.delete(categoryEntity);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryEntity> categoryEntityList=categoryRepository.findAll();
        List<CategoryDto> categoryDtos=categoryEntityList.stream().map(categoryEntity -> convertToCategoryDto(categoryEntity)).collect(Collectors.toList());

        return categoryDtos;
    }

    CategoryDto convertToCategoryDto(CategoryEntity categoryEntity){
        CategoryDto categoryDto=modelMapper.map(categoryEntity,CategoryDto.class);
        return categoryDto;
    }

    CategoryEntity convertToCategoryEntity(CategoryDto categoryDto){
        CategoryEntity categoryEntity=modelMapper.map(categoryDto,CategoryEntity.class);
        return categoryEntity;
    }
}
