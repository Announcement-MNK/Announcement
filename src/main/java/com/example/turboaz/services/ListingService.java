package com.example.turboaz.services;

import com.example.turboaz.dtos.ListingCreationDto;
import com.example.turboaz.dtos.ListingGetDto;
import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.enums.Loan;

import java.util.List;

/**
 * Listing CRUD operations.
 */
public interface ListingService {

    /**
     * @param listingCreateDto
     * @return listingGetDto
     */
    ListingGetDto createListing(ListingCreationDto listingCreateDto);

    /**
     * @param listingCreateDto
     * @return listingGetDto
     */
    ListingGetDto updateListing(Long id, ListingCreationDto listingCreateDto);

    /**
     * @param id
     */
    void deleteListing(Long id);

    /**
     * @return listingListDto
     */
    List<ListingListDto> getAllListings();

    /**
     * @param id
     * @return listingGetDto
     */
    ListingGetDto getListing(Long id);

    /**
     *
     * @param makeId
     * @param modelId
     * @param cityId
     * @param minYear
     * @param maxYear
     * @param minPrice
     * @param maxPrice
     * @param minMillage
     * @param maxMillage
     * @param fuelType
     * @param loan
     * @param bodyType
     * @param gearbox
     * @param transactionType
     * @param specIds
     * @param color
     * @return List<ListingGetDto>
     */
    List<ListingGetDto> search(Long makeId, Long modelId, Long cityId, Integer minYear, Integer maxYear,
                               Integer minPrice, Integer maxPrice, Integer minMillage, Integer maxMillage,
                               String fuelType, String loan, String bodyType, String gearbox, String transactionType,
                               List<Long> specIds, String color);

}
