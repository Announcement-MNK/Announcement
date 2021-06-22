package com.example.turboaz.services;

import com.example.turboaz.dtos.*;
import com.example.turboaz.enums.ListingType;
import com.example.turboaz.exceptions.ListingNotFoundException;
import com.example.turboaz.exceptions.UserNotFoundException;
import com.example.turboaz.models.*;
import com.example.turboaz.repositories.*;
import com.example.turboaz.helpers.DtoHelper;
import com.example.turboaz.utils.Paging;
import com.example.turboaz.helpers.PagingHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.turboaz.helpers.PagingHelper.preparePage;

@Service
public class ListingServiceImpl implements  ListingService{

    ListingRepository listingRepository;
    CityRepository cityRepository;
    ModelRepository modelRepository;
    UserRepository userRepository;
    CarSpecificationRepository carSpecificationRepository;
    TransactionService transactionService;

    public ListingServiceImpl(ListingRepository listingRepository,
                              CityRepository cityRepository,
                              ModelRepository modelRepository,
                              UserRepository userRepository,
                              CarSpecificationRepository carSpecificationRepository,
                              TransactionService transactionService){
        this.listingRepository = listingRepository;
        this.cityRepository = cityRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.carSpecificationRepository = carSpecificationRepository;
        this.transactionService = transactionService;
    }

    @Override
    public ListingGetDto createListing(String username, ListingCreationDto listingCreateDto) {
        Listing listing = convertToEntity(username, listingCreateDto);
        return new ListingGetDto(listingRepository.save(listing));
    }

    @Override
    public ListingGetDto updateListing(String username, Long id, ListingCreationDto listingCreateDto)
            throws ListingNotFoundException {
        User user = userRepository.findUserByUsername(username); //TODO
        Listing listing = listingRepository.findListingByUserIdAndId(user.getId(), id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        Listing newListing = convertToEntity(username, listingCreateDto);
        newListing.setId(id);
        return new ListingGetDto(listingRepository.save(newListing));
    }

    @Override
    public void deleteListing(String username, Long id) throws ListingNotFoundException {
        User user = userRepository.findUserByUsername(username); //TODO
        Listing listing = listingRepository.findListingByUserIdAndId(user.getId(), id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        listingRepository.delete(listing);
    }

    @Override
    public Paging<ListingListDto> getAllListings(int index, int size, String sortBy) {
        Pageable paging = preparePage(index-1, size, sortBy);
        Page<Listing> listings =  listingRepository.findAll(paging);
        return new Paging<ListingListDto>().toBuilder()
                .pageCount((long) listings.getTotalPages())
                .pageSize(listings.getTotalElements())
                .items(DtoHelper.convertToListingListDto(PagingHelper.getResult(listings)))
                .build();
    }

    @Override
    public List<Listing> getAllExpiredListings() {
        return listingRepository.findAllExpired();
    }

    @Override
    public List<Listing> getAllTomorrowExpiredListings() {
        return listingRepository.findAllTomorrowExpired();
    }

    @Override
    public Paging<ListingListDto> getUserListings(String username, int index, int size, String sortBy) {
        Pageable paging = preparePage(index, size, sortBy);
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

    @Override
    public void makeVip(String username, Long id) throws ListingNotFoundException, UserNotFoundException {
        User user = userRepository.findUserByUsername(username); //TODO
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        listing.get().setType(ListingType.VIP);
        listingRepository.save(listing.get());
        transactionService.createTransaction(user.getId(), new TransactionPostDto(id, ListingType.VIP.getAmount()));
    }

    @Override
    public void makePaid(String username, Long id) throws ListingNotFoundException, UserNotFoundException {
        User user = userRepository.findUserByUsername(username); //TODO
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing == null) throw new ListingNotFoundException("Can't find such listing");
        listing.get().setType(ListingType.PAID);
        listingRepository.save(listing.get());
        transactionService.createTransaction(user.getId(), new TransactionPostDto(id, ListingType.PAID.getAmount()));
    }

    @Override
    public Paging<ListingListDto> search(Specification<Listing> spec, Integer size, Integer index) {
        Pageable paging = preparePage(index - 1, size, "id");
        Page<Listing> listings = listingRepository.findAll(spec, paging);
        return new Paging<ListingListDto>().toBuilder()
                .pageCount((long) listings.getTotalPages())
                .pageSize(listings.getTotalElements())
                .items(DtoHelper.convertToListingListDto(PagingHelper.getResult(listings)))
                .build();
    }

    public Listing convertToEntity(String username, ListingCreationDto listingCreateDto){
        List<CarSpecification> carSpecifications = new ArrayList<>();
        if (listingCreateDto.getCarSpecIds() != null) {
            listingCreateDto.getCarSpecIds().stream()
                    .forEach(id -> carSpecifications.add(carSpecificationRepository.getCarSpecificationById(id)));
        }
        Model model = modelRepository.findById(listingCreateDto.getModelId()).get();
        City city = cityRepository.findById(listingCreateDto.getCityId()).get();
        User user = userRepository.findUserByUsername(username);
        Listing listing = DtoHelper.convertListingCreationDtoToEntity(listingCreateDto, model, city,user, carSpecifications);
        return listing;
    }

}
