package com.example.turboaz.services;

import com.example.turboaz.dtos.ListingCreationDto;
import com.example.turboaz.dtos.ListingGetDto;
import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.models.CarSpecification;
import com.example.turboaz.models.City;
import com.example.turboaz.models.Listing;
import com.example.turboaz.models.Model;
import com.example.turboaz.repositories.CarSpecificationRepository;
import com.example.turboaz.repositories.CityRepository;
import com.example.turboaz.repositories.ListingRepository;
import com.example.turboaz.repositories.ModelRepository;
import com.example.turboaz.helpers.DtoHelper;
import com.example.turboaz.utils.Paging;
import com.example.turboaz.helpers.PagingHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListingServiceImpl implements  ListingService{

    ListingRepository listingRepository;
    CityRepository cityRepository;
    ModelRepository modelRepository;
    CarSpecificationRepository carSpecificationRepository;

    public ListingServiceImpl(ListingRepository listingRepository,
                              CityRepository cityRepository,
                              ModelRepository modelRepository,
                              CarSpecificationRepository carSpecificationRepository){
        this.listingRepository = listingRepository;
        this.cityRepository = cityRepository;
        this.modelRepository = modelRepository;
        this.carSpecificationRepository = carSpecificationRepository;
    }

    @Override
    public ListingGetDto createListing(Long userId, ListingCreationDto listingCreateDto) {
        Listing listing = convertToEntity(listingCreateDto);
        return new ListingGetDto(listingRepository.save(listing));
    }

    @Override
    public ListingGetDto updateListing(Long userId, Long id, ListingCreationDto listingCreateDto)
            throws ListingNotFoundException {
        Listing listing = listingRepository.findListingByUserIdAndId(userId, id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        Listing newListing = convertToEntity(listingCreateDto);
        newListing.setId(id);
        return new ListingGetDto(listingRepository.save(newListing));
    }

    @Override
    public void deleteListing(Long userId, Long id) throws ListingNotFoundException {
        Listing listing = listingRepository.findListingByUserIdAndId(userId, id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        listingRepository.delete(listing);
    }

    @Override
    public Paging<ListingListDto> getAllListings(int index, int size, String sortBy) {
        Pageable paging = PagingHelper.preparePage(index, size, sortBy);
        Page<Listing> listings =  listingRepository.findAll(paging);
        return new Paging<ListingListDto>().toBuilder()
                .pageCount((long) listings.getTotalPages())
                .pageSize(listings.getTotalElements())
                .items(DtoHelper.convertToListingListDto(PagingHelper.getResult(listings)))
                .build();
    }

    @Override
    public Paging<ListingListDto> getUserListings(String username, int index, int size, String sortBy) {
        Pageable paging = PagingHelper.preparePage(index, size, sortBy);
        Page<Listing> listings =  listingRepository.findListingsByUsername(username, paging);
        return new Paging<ListingListDto>().toBuilder()
                .pageCount((long) listings.getTotalPages())
                .pageSize(listings.getTotalElements())
                .items(DtoHelper.convertToListingListDto(PagingHelper.getResult(listings)))
                .build();
    }

    @Override
    public ListingGetDto getListing(Long id) throws ListingNotFoundException {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        return new ListingGetDto(listing.get());
    }


    //TODO FINISH SEARCH
    @Override
    public List<ListingGetDto> search(Long makeId, Long modelId, Long cityId, Integer minYear, Integer maxYear, Integer minPrice, Integer maxPrice, Integer minMillage, Integer maxMillage, String fuelType, String loan, String bodyType, String gearbox, String transactionType, List<Long> specIds, String color) {
        return null;
    }

    public Listing convertToEntity(ListingCreationDto listingCreateDto){
        List<CarSpecification> carSpecifications = new ArrayList<>();
        listingCreateDto.getCarSpecIds().stream()
                .forEach(id -> carSpecifications.add(carSpecificationRepository.getCarSpecificationById(id)));
        Model model = modelRepository.findById(listingCreateDto.getModelId()).get();
        City city = cityRepository.findById(listingCreateDto.getCityId()).get();
        Listing listing = DtoHelper.convertListingCreationDtoToEntity(listingCreateDto, model, city, carSpecifications);
        return listing;
    }

}
