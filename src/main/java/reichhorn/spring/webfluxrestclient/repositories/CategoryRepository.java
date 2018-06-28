package reichhorn.spring.webfluxrestclient.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reichhorn.spring.webfluxrestclient.model.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
