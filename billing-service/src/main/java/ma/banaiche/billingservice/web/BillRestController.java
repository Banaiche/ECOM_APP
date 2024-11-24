package ma.banaiche.billingservice.web;

import ma.banaiche.billingservice.entities.Bill;
import ma.banaiche.billingservice.entities.ProductItem;
import ma.banaiche.billingservice.feign.CustomerRestClient;
import ma.banaiche.billingservice.feign.ProductRestClient;
import ma.banaiche.billingservice.repositories.BillRepository;
import ma.banaiche.billingservice.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }

    @GetMapping("/bills")
    public List<Bill> getBills() {
        List<Bill> bills = billRepository.findAll();
        bills.forEach(bill -> bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId())));
        bills.forEach(bill -> bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        }));
        return bills;
    }
}
