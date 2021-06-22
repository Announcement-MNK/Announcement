package com.example.turboaz.utils;

import com.example.turboaz.enums.BodyType;
import com.example.turboaz.enums.FuelType;
import com.example.turboaz.models.Listing;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil {
    public static Specification<Listing> sameMake(String makeName) {
        return (root, query, criteriaBuilder) -> {
            if (makeName != null)
                return criteriaBuilder.equal(root.get("baseListing").get("make").get("name"), makeName);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameModel(String modelName) {
        return (root, query, criteriaBuilder) -> {
            if (modelName != null)
                return criteriaBuilder.equal(root.get("baseListing").get("model").get("name"), modelName);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameLocation(String location) {
        return (root, query, criteriaBuilder) -> {
            if (location != null)
                return criteriaBuilder.equal(root.get("baseListing").get("city").get("name"), location);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameBodyType(String bodyType) {
        return (root, query, criteriaBuilder) -> {
            if (bodyType != null)
                return criteriaBuilder.equal(root.get("bodyType"), BodyType.valueOf(bodyType));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> sameFuelType(String fuelType) {
        return (root, query, criteriaBuilder) -> {
            if (fuelType != null)
                return criteriaBuilder.equal(root.get("fuelType"), FuelType.valueOf(fuelType));
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> hasLeaseOption(Boolean loanOption) {
        return (root, query, criteriaBuilder) -> {
            if (loanOption != null)
                return criteriaBuilder.equal(root.get("baseListing").get("leaseOption"), loanOption);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> hasCreditOption(Boolean creditOption) {
        return (root, query, criteriaBuilder) -> {
            if (creditOption != null)
                return criteriaBuilder.equal(root.get("baseListing").get("creditOption"), creditOption);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> hasBarterOption(Boolean barterOption) {
        return (root, query, criteriaBuilder) -> {
            if (barterOption != null)
                return criteriaBuilder.equal(root.get("baseListing").get("barterOption"), barterOption);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> hasCashOption(Boolean cashOption) {
        return (root, query, criteriaBuilder) -> {
            if (cashOption != null)
                return criteriaBuilder.equal(root.get("baseListing").get("cashOption"), cashOption);
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Listing> betweenYears(Integer minYear, Integer maxYear) {
        return (root, query, criteriaBuilder) -> {
            if (minYear == null && maxYear == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.between(root.get("baseListing").get("year"), minYear != null ? minYear : 0,
                    maxYear != null ? maxYear : Integer.MAX_VALUE);
        };
    }

    public static Specification<Listing> betweenPrice(Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.between(root.get("baseListing").get("price"), minPrice != null ? minPrice : 0,
                    maxPrice != null ? maxPrice : Integer.MAX_VALUE);
        };
    }

    public static Specification<Listing> betweenMileage(Integer minMileage, Integer maxMileage) {
        return (root, query, criteriaBuilder) -> {
            if (minMileage == null && maxMileage == null)
                return criteriaBuilder.conjunction();
            return criteriaBuilder.between(root.get("baseListing").get("mileage"), minMileage != null ? minMileage : 0,
                    maxMileage != null ? maxMileage : Integer.MAX_VALUE);
        };
    }
}