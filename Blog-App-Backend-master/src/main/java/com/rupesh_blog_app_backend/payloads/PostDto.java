package com.rupesh_blog_app_backend.payloads;

import lombok.*;


import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    private int postId;

    @Size(min = 4,message = "title must be min 4 charactes !!")
    private String title;

    @Size(min = 10,message = "title must be min 10 charactes !!")
    private String content;

    private String imageName;

    private Date addedDate;

    private UserDto userEntity;

    private CategoryDto categoryEntity;


}
