package online.fivediamond.be.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import online.fivediamond.be.enums.Gender;

import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double price;
    double priceRate;
    String imgURL;
    double weight;

    @Enumerated(EnumType.STRING)
    Gender gender;

    boolean isSpecial;
    boolean isDeleted;
    boolean isSale;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToMany
    @JoinTable(name = "product_material",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    Set<Material> materials;

}
