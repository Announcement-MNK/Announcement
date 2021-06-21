package com.example.turboaz.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile multipartFile);

    void delete(String filename);
}
