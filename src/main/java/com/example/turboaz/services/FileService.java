package com.example.turboaz.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * Upload new file
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile);
    /**
     * Delete file by filename
     */

    void delete(String filename);
}
