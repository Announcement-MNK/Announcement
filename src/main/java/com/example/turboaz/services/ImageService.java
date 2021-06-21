package com.example.turboaz.services;

import com.example.turboaz.dtos.ImageDto;
import com.example.turboaz.exceptions.ImageNotFoundException;
import com.example.turboaz.exceptions.ListingNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    /**
     * Search for all images in given listing
     * @param listingId
     * @return List<ImageDto>
     */
    List<ImageDto> getImages(Long listingId);

    /**
     * Search for image in given listing by its id
     * @param listingId
     * @param id
     * @return ImageDto
     * @throws ListingNotFoundException
     * @throws ImageNotFoundException
     */
    ImageDto getImageById(Long listingId, Long id) throws ListingNotFoundException, ImageNotFoundException;

    /**
     * Upload new image
     * @param username
     * @param listingId
     * @param file
     * @return ImageDto
     * @throws ListingNotFoundException
     */
    ImageDto uploadImage(String username, Long listingId, MultipartFile file) throws ListingNotFoundException;
    void deleteImage(String username, Long listingId, Long id) throws ListingNotFoundException, ImageNotFoundException;
}
