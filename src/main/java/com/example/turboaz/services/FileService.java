package com.example.turboaz.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileService {

    private static final String MY_URL = "https://firebasestorage.googleapis.com/v0/b/turboaz-e8cff.appspot.com/o/%s?alt=media";
    String TEMP_URL = "";

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("turboaz-e8cff.appspot.com", fileName);
        String type = Files.probeContentType(file.toPath());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(type).build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(MY_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    public Object upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
            File file = this.convertToFile(multipartFile, fileName);
            TEMP_URL = this.uploadFile(file, fileName);
            file.delete();
            return TEMP_URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "400";
        }
    }
}
