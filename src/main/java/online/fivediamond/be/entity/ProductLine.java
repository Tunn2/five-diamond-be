package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.enums.Gender;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.TypeOfSub;

import java.util.Set;

@Entity
@Getter
@Setter
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    Gender gender;

    double priceRate;
    double price;
    double weight;
    String metal;
    String karat;
    String imgURL;
    @Enumerated(EnumType.STRING)
    TypeOfSub typeOfSub;
    int quantityOfSub;
    int quantity;
    boolean isDeleted = false;
    boolean isSpecial;

    String shape;
    String cut;
    String color;
    String clarity;
    double carat;
    double size;
    Origin origin;

    @ManyToOne
    @JoinColumn(name = "product_line_id")
    Collection collection;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "productLine", cascade = CascadeType.ALL)
    Set<Product> products;

    @OneToOne(mappedBy = "productLine")
    CartItem cartItem;
}
