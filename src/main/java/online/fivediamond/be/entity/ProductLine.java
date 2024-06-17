package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String description;

    @OneToMany(mappedBy = "productLine", cascade = CascadeType.ALL)
    Set<Product> products;
}
