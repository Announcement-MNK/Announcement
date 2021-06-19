package com.example.turboaz.helpers;

import com.example.turboaz.dtos.ListingCreationDto;
import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.enums.*;
import com.example.turboaz.models.*;
import org.dom4j.rule.Mode;

import java.util.ArrayList;
import java.util.List;

public class DtoHelper {
    public static Listing convertListingCreationDtoToEntity(ListingCreationDto dto,
                                                            Model model, City city,
                                                            List<CarSpecification> carSpecifications){
        Listing listing = new Listing();
        listing.setBodyType(BodyType.valueOf(dto.getBodyType()));
        listing.setCity(city);
        listing.setModel(model);
        listing.setColor(Color.valueOf(dto.getColor()));
        listing.setPrice(dto.getPrice());
        listing.setYear(dto.getYear());
        listing.setMileage(dto.getMileage());
        listing.setGearBox(GearBox.valueOf(dto.getGearBox()));
        listing.setFuelType(FuelType.valueOf(dto.getFuelType()));
        listing.setAutoPay(dto.isAutoPay());
        listing.setBarterOption(dto.isBarterOption());
        listing.setLeaseOption(dto.isLeaseOption());
        listing.setCashOption(dto.isCashOption());
        listing.setCreditOption(dto.isCreditOption());
        listing.setDescription(dto.getDescription());
        listing.setType(ListingType.valueOf(dto.getType()));
        listing.setCarSpecifications(carSpecifications);
        return listing;
    }

    public static List<ListingListDto> convertToListingListDto(List<Listing> listings){
        List<ListingListDto> listingListDtos = new ArrayList<>();
        listings.forEach(l -> listingListDtos.add(new ListingListDto(l)));
        return listingListDtos;
    }

}
