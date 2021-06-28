//package com.example.turboaz;
//
//import com.example.turboaz.repositories.ListingRepository;
//import com.example.turboaz.services.ListingService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Collections;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@WebMvcTest
//class TurboAzApplicationTests {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    ListingService listingService;
//
//    @Test
//    void contextLoads() throws Exception {
//
//        when(listingService.getAllListings(1, 10, "id")).thenReturn(
//                Collections.emptyList()
//        );
//        MvcResult mvcResult = mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/v1/listings")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn();
//
//        System.out.println(mvcResult.getResponse());
//
//        verify(listingRepository).findAll();
//    }
//}
