package com.example.turboaz.services;

import com.example.turboaz.dtos.ImageDto;
import com.example.turboaz.exceptions.ImageNotFoundException;
import com.example.turboaz.exceptions.ListingNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<ImageDto> getImages(Long listingId);
    ImageDto getImageById(Long listingId, Long id) throws ListingNotFoundException, ImageNotFoundException;
    ImageDto uploadImage(String username, Long listingId, MultipartFile file) throws ListingNotFoundException;
    void deleteImage(String username, Long listingId, Long id) throws ListingNotFoundException, ImageNotFoundException;
}
