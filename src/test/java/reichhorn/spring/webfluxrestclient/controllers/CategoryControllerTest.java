package reichhorn.spring.webfluxrestclient.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reichhorn.spring.webfluxrestclient.model.Category;
import reichhorn.spring.webfluxrestclient.repositories.CategoryRepository;

import static org.junit.Assert.*;

public class CategoryControllerTest {

    WebTestClient webTestClient;

    @Mock
    CategoryRepository categoryRepository;

    CategoryController categoryController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryController = new CategoryController(categoryRepository);

        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getAllCategories() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("Fruits").build(),
                        Category.builder().description("Nuts").build()));

        webTestClient.get()
                .uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    public void getCategorieById() {
        BDDMockito.given(categoryRepository.findById("someId"))
                .willReturn(Mono.just(Category.builder().description("Meat").build()));

        webTestClient.get()
                .uri("/api/v1/categories/someId")
                .exchange()
                .expectBody(Category.class);
    }
}