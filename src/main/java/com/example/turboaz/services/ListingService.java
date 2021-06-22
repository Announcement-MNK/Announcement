package com.example.turboaz.services;

import com.example.turboaz.dtos.ListingCreationDto;
import com.example.turboaz.dtos.ListingGetDto;
import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.enums.Loan;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.models.Listing;
import com.example.turboaz.utils.Paging;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Listing CRUD operations.
 */
public interface ListingService {

    /**
     * @param listingCreateDto
     * @return listingGetDto
     */
    ListingGetDto createListing(String username, ListingCreationDto listingCreateDto);

    /**
     * @param listingCreateDto
     * @return listingGetDto
     */
    ListingGetDto updateListing(String username, Long id, ListingCreationDto listingCreateDto) throws ListingNotFoundException;

    /**
     * @param id
     */
    void deleteListing(String username, Long id) throws ListingNotFoundException;

    /**
     * @return listingListDto
     */
    Paging<ListingListDto> getAllListings(int index, int size, String sortBy);

    /**
     * @return listingListDto
     */
    Paging<ListingListDto> getUserListings(String username, int index, int size, String sortBy);

    /**
     * @param id
     * @return listingGetDto
     */
    ListingGetDto getListing(Long id) throws ListingNotFoundException;

    /**
     *
     * @param id
     * @throws ListingNotFoundException
     */
    void makeVip(Long id) throws ListingNotFoundException;


    /**
     *
     * @param id
     * @throws ListingNotFoundException
     */
    void makePaid(Long id) throws ListingNotFoundException;


    Paging<ListingListDto> search(Specification<Listing> spec, Integer size, Integer index);

}
