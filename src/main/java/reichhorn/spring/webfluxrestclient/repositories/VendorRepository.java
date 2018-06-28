package reichhorn.spring.webfluxrestclient.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reichhorn.spring.webfluxrestclient.model.Vendor;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
