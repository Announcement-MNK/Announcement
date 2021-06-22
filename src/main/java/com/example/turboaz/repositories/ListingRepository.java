package com.example.turboaz.repositories;

import com.example.turboaz.models.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ListingRepository extends PagingAndSortingRepository<Listing, Long>,
        JpaSpecificationExecutor<Listing> {

    //TODO 10 % of result must be VIP
    @Query(value = "SELECT l FROM Listing l WHERE l.user.username = :username", nativeQuery = true)
    Page<Listing> findListingsByUsername(String username, Pageable pageable);

    Listing findListingByUserIdAndId(Long userId, Long id);
}
