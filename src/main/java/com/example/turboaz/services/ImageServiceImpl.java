package com.example.turboaz.services;

import com.example.turboaz.dtos.ImageDto;
import com.example.turboaz.exceptions.ImageNotFoundException;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.helpers.DtoHelper;
import com.example.turboaz.models.Image;
import com.example.turboaz.models.Listing;
import com.example.turboaz.models.User;
import com.example.turboaz.repositories.ImageRepository;
import com.example.turboaz.repositories.ListingRepository;
import com.example.turboaz.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    ImageRepository repository;
    FileService fileService;
    ListingRepository listingRepository;
    UserRepository userRepository;

    public ImageServiceImpl(ImageRepository repository,
                            FileService fileService,
                            ListingRepository listingRepository,
                            UserRepository userRepository){
        this.repository = repository;
        this.fileService = fileService;
        this.listingRepository = listingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ImageDto> getImages(Long listingId) {
        return DtoHelper.convertImagestoImageDtos(repository.getAllByListingId(listingId));
    }

    @Override
    public ImageDto getImageById(Long listingId, Long id) throws ListingNotFoundException, ImageNotFoundException {
        Optional<Listing> listing = listingRepository.findById(listingId);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        Image image = repository.findImageByListingIdAndId(listingId, id);
        if (image == null) throw new ImageNotFoundException("Can't find such image");
        return new ImageDto(image);
    }

    @Override
    public ImageDto uploadImage(String username, Long listingId, MultipartFile file) throws ListingNotFoundException {
        User user = userRepository.findUserByUsername(username); //TODO exception
        Listing listing = listingRepository.findListingByUserIdAndId(user.getId(), listingId);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        String url = fileService.upload(file);
        Image image = new Image();
        image.setUrl(url);
        image.setListing(listing);
        return new ImageDto(repository.save(image));
    }

    @Override
    public void deleteImage(String username, Long listingId, Long id) throws ListingNotFoundException, ImageNotFoundException {
        User user = userRepository.findUserByUsername(username); //TODO exception
        Listing listing = listingRepository.findListingByUserIdAndId(user.getId(), listingId);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        Image image = repository.findImageByListingIdAndId(listingId, id);
        if (image == null) throw new ImageNotFoundException("Can't find such image");
        repository.delete(image);
    }
}
