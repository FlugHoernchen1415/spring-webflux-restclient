package reichhorn.spring.webfluxrestclient.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reichhorn.spring.webfluxrestclient.model.Vendor;
import reichhorn.spring.webfluxrestclient.repositories.VendorRepository;

import static org.junit.Assert.*;

public class VendorControllerTest {

    WebTestClient webTestClient;

    @Mock
    VendorRepository vendorRepository;

    @InjectMocks
    VendorController vendorController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void getAllVendors() {

        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName("Elon").lastName("Musk").build(),
                        Vendor.builder().firstName("Steve").lastName("Jobs").build()));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void getVendorById() {

        BDDMockito.given(vendorRepository.findById("someId"))
                .willReturn(Mono.just(Vendor.builder().firstName("Jack").lastName("Dorsey").build()));

        webTestClient.get()
                .uri("/api/v1/vendors/someId")
                .exchange()
                .expectBody(Vendor.class);
    }
}