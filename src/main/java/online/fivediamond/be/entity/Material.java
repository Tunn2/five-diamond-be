package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import online.fivediamond.be.enums.Type;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    String imgURL;
    double price;
    String origin;
    Type type;
    String metal;
    String karat;
    int quantityOfSub;
    double weightOfSub;
    String typeOfSub;

//    @OneToMany(mappedBy = "material")
//    List<ProductMaterial> productMaterials;

    @OneToOne
    @JoinColumn(name = "certificate_id")
    Certificate certificate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_material",
            joinColumns = @JoinColumn(name = "material_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Product> products;
}

