package reichhorn.spring.webfluxrestclient.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reichhorn.spring.webfluxrestclient.model.Category;
import reichhorn.spring.webfluxrestclient.repositories.CategoryRepository;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Category> getCategorieById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }
}
