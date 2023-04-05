package com.rupesh_blog_app_backend.services.impl;

import com.rupesh_blog_app_backend.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImp implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file,String fileName) throws IOException {

        String name= file.getOriginalFilename();
        String fileName1=fileName.concat(name.substring(name.lastIndexOf(".")));

        String filePath=path+ File.separator+fileName1;

        File f=new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        File previousFile=new File(filePath);
        if (previousFile.exists()){
            previousFile.delete();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
