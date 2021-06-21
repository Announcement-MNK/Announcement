package com.example.turboaz.repositories;

import com.example.turboaz.models.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> getAllByListingId(Long id);
    Image getById(Long id);
    Image findImageByListingIdAndId(Long listingId, Long id);
}
