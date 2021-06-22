package com.example.turboaz.controllers;

import com.example.turboaz.dtos.ListingCreationDto;
import com.example.turboaz.dtos.ListingGetDto;
import com.example.turboaz.dtos.ListingListDto;
import com.example.turboaz.dtos.UserDto;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.services.FileService;
import com.example.turboaz.services.ListingService;
import com.example.turboaz.utils.Paging;
import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.turboaz.utils.SpecificationUtil.*;

@RestController
@RequestMapping("/api/v1")
public class ListingController {

    ListingService service;
    FileService fileService;

    public ListingController(ListingService service, FileService fileService){
        this.service = service;
        this.fileService = fileService;
    }

    /**
     * gets all listings paginated
     *
     * @return
     */
    @GetMapping("/listings")
    public ResponseEntity<Paging<ListingListDto>> getAll(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "1") int page) throws BadHttpRequest {
        return new ResponseEntity<>(service.getAllListings(page, size, "id"), HttpStatus.OK);
    }


    /**
     * Gets listing by id
     * @param id
     * @return
     * @throws ListingNotFoundException
     */
    @GetMapping("/listings/{id}")
    public ResponseEntity<ListingGetDto> get(@PathVariable long id ) throws ListingNotFoundException {
        return new ResponseEntity<>(service.getListing(id), HttpStatus.OK);
    }

    /**
     * creates listing by given data
     * @param creationDto
     * @param userDto
     * @return
     */
    @PostMapping("/listings")
    public ResponseEntity<ListingGetDto> create(@RequestBody ListingCreationDto creationDto,
                                                @RequestAttribute("user") UserDto userDto){
        return new ResponseEntity<>(service.createListing(userDto.getUsername(), creationDto), HttpStatus.CREATED);
    }

    /**
     * updates listing by id
     * @param creationDto
     * @param userDto
     * @param id
     * @return
     * @throws ListingNotFoundException
     */
    @PutMapping("/listings/{id}")
    public ResponseEntity<ListingGetDto> update(@RequestBody ListingCreationDto creationDto,
                                                @RequestAttribute("user") UserDto userDto,
                                                @PathVariable Long id) throws ListingNotFoundException {
        return new ResponseEntity<>(service.updateListing(userDto.getUsername(), id, creationDto), HttpStatus.CREATED);
    }

    /**
     * deletes listing by id
     * @param userDto
     * @param id
     * @return
     * @throws ListingNotFoundException
     */
    @DeleteMapping("/listings/{id}")
    public ResponseEntity delete(@RequestAttribute("user") UserDto userDto,
                                                @PathVariable Long id) throws ListingNotFoundException {
        service.deleteListing(userDto.getUsername(), id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * makes type of listing vip by id
     * @param id
     * @return
     * @throws ListingNotFoundException
     */
    @PutMapping("/listings/{id}/makevip")
    public ResponseEntity makeVip(@PathVariable Long id) throws ListingNotFoundException {
        service.makeVip(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * makes type of listing paid by id
     * @param id
     * @return
     * @throws ListingNotFoundException
     */
    @PutMapping("/listings/{id}/makepaid")
    public ResponseEntity makePaid(@PathVariable Long id) throws ListingNotFoundException {
        service.makePaid(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/listings/search")
    public ResponseEntity<Paging<ListingListDto>> get(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minMileage,
            @RequestParam(required = false) Integer maxMileage,
            @RequestParam(required = false) String fuelType,
            @RequestParam(required = false) String bodyType,
            @RequestParam(required = false) Boolean loanOption,
            @RequestParam(required = false) Boolean barterOption,
            @RequestParam(required = false) Boolean leaseOption,
            @RequestParam(required = false) Boolean cashOption,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "1") Integer index
    ) {
        return new ResponseEntity<>(service.search(sameMake(make)
                        .and(sameModel(model))
                        .and(sameLocation(location))
                        .and(betweenYears(minYear, maxYear))
                        .and(betweenPrice(minPrice, maxPrice))
                        .and(betweenMileage(minMileage, maxMileage))
                        .and(sameFuelType(fuelType))
                        .and(sameBodyType(bodyType))
                        .and(hasCreditOption(loanOption))
                        .and(hasBarterOption(barterOption))
                        .and(hasLeaseOption(leaseOption))
                        .and(hasCashOption(cashOption)),
                size,
                index
        ), HttpStatus.OK);
    }

}
