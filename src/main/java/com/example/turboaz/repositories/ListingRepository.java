package com.example.turboaz.repositories;

import com.example.turboaz.models.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ListingRepository extends PagingAndSortingRepository<Listing, Long> {
    Page<Listing> findAll(Pageable pageable);

    Page<Listing> findListingsByUserId(Long id, Pageable pageable);
}
