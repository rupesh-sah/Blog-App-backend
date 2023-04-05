package com.rupesh_blog_app_backend.payloads;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class RoleDto {
    private int roleId;
    private String name;
}
