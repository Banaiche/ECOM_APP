package ma.banaiche.inventoryservice;

import ma.banaiche.inventoryservice.entities.Product;
import ma.banaiche.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.Customizer;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Laptop")
                    .price(1000)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Nintendo Switch Oled")
                    .price(2100)
                    .quantity(14)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Computer")
                    .price(9000)
                    .quantity(34)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Phone")
                    .price(2600)
                    .quantity(48)
                    .build());

            productRepository.findAll().forEach(
                    customer -> {
                        System.out.println("============================");
                        System.out.println(customer.getId());
                        System.out.println(customer.getName());
                        System.out.println(customer.getPrice());
                        System.out.println(customer.getQuantity());
                        System.out.println("============================");
                    }
            );
        };

    }

}
