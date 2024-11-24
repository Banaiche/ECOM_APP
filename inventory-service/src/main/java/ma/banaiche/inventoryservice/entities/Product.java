package ma.banaiche.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
}
