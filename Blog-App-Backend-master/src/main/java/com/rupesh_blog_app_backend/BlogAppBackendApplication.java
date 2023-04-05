package com.rupesh_blog_app_backend;

import com.rupesh_blog_app_backend.config.AppConstants;
import com.rupesh_blog_app_backend.entity.Role;
import com.rupesh_blog_app_backend.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogAppBackendApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(BlogAppBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
         System.out.println(passwordEncoder.encode("xyz"));
         try {
             List<Role> roleList=new ArrayList<>();
             roleList.add(new Role(AppConstants.ROLE_ADMIN,"ROLE_ADMIN"));
             roleList.add(new Role(AppConstants.ROLE_NORMAL,"ROLE_NORMAL"));
             List<Role> result=roleRepository.saveAll(roleList);

             System.out.println(result);

         }catch (Exception e){
             System.out.println(e);
         }

    }
}
