package reichhorn.spring.webfluxrestclient.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reichhorn.spring.webfluxrestclient.model.Category;
import reichhorn.spring.webfluxrestclient.model.Vendor;
import reichhorn.spring.webfluxrestclient.repositories.CategoryRepository;
import reichhorn.spring.webfluxrestclient.repositories.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("#### LOADING DATA ON BOOTSTRAP ####");
        loadCategories();
        loadVendors();
    }

    private void loadCategories() {

        if(categoryRepository.count().block() == 0) {

            categoryRepository.save(Category.builder()
                    .description("Fruits").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Nuts").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Breads").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Meats").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Eggs").build()).block();
        }

        System.out.println(categoryRepository.count().block() + " Categories Loaded");
    }

    private void loadVendors() {

        if(vendorRepository.count().block() == 0) {

            vendorRepository.save(Vendor.builder()
                    .firstName("Jon")
                    .lastName("Doe").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Michael")
                    .lastName("Weston").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Elon")
                    .lastName("Musk").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Steve")
                    .lastName("Jobs").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Jimmy")
                    .lastName("Buffet").build()).block();
        }

        System.out.println(vendorRepository.count().block() + " Vendors loaded");
    }
}
