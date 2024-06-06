package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    double price;
    double priceRate;
    String imgURL;

    double weight;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    boolean isDeleted;

    @ManyToMany(mappedBy = "products")
    Set<Material> materials;

}
