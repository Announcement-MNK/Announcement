package com.example.turboaz.repositories;

import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.Color;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.models.Listing;
import com.example.turboaz.models.Subscription;
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

    String a = "         ";
    String b = "          \n" +
            "            ";

    @Query(value = "select s from Subscription s where s.color=:color and s.bodyType=:bodyType and s.city.id=:cityId and s.fuelType=:fuelType  and s.hasCash=:hasCash and s.hasLoan=:hasLoan  and s.hasLease=:hasLease and s.make.id=:makeId and s.maxPrice>=:maxPrice and s.minPrice<=:minPrice and s.maxMileage>=:maxMileage  and s.minMileage<=:minMileage  and s.maxYear>=:maxYear and s.minYear<=:minYear and s.model.id=:modelId")
    List<Subscription> getSubscriptionForEmail(Color color, BodyType bodyType, long cityId, FuelType fuelType
            , boolean hasCash , boolean hasLoan, boolean hasLease , long makeId, int maxPrice, int minPrice, double maxMileage, double minMileage, int maxYear, int minYear, long modelId);

    Listing findListingByUserIdAndId(Long userId, Long id);

    @Query(value = "SELECT l FROM Listing l WHERE l.updated_at + INTERVAL '1 MONTH' < now()", nativeQuery = true)
    List<Listing> findAllExpired();

    @Query(value = "SELECT l FROM Listing l WHERE l.updated_at + INTERVAL '1 MONTH' = now() + INTERVAL '1 DAY'", nativeQuery = true)
    List<Listing> findAllTomorrowExpired();
}
