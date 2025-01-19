package ma.banaiche.customerservice;

import ma.banaiche.customerservice.config.CustomerConfigParams;
import ma.banaiche.customerservice.entities.Customer;
import ma.banaiche.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder()
					.name("Abdelali")
							.email("abdelali@gmail.com")
					.build());
			customerRepository.save(Customer.builder().
					name("Mohamed")
							.email("mohamed@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
							.name("Youness")
							.email("youness@gmail.com")
					.build());

			customerRepository.findAll().forEach(
					customer -> {
						System.out.println("============================");
						System.out.println(customer.getId());
						System.out.println(customer.getName());
						System.out.println(customer.getEmail());
						System.out.println("============================");
					}
			);
		};
	}

}
