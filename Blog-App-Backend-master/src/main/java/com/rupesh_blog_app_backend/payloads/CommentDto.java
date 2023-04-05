package com.rupesh_blog_app_backend.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Data
@Setter
@Getter
@NoArgsConstructor
public class CommentDto {
    private int commentId;

    @Size(min = 4,message = "comment must be min 4 charactes !!")
    private String content;

    private UserDto userEntity;
    private PostDto postEntity;

}
