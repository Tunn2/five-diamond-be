package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.Type;

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

    @Enumerated(EnumType.STRING)
    Origin origin;

    @Enumerated(EnumType.STRING)
    Type type;
    String metal;
    String karat;
    int quantityOfSub;
    double caratOfSub;
    String typeOfSub;

    @OneToOne
    @JoinColumn(name = "certificate_id")
    Certificate certificate;

    @ManyToMany(mappedBy = "materials")
    @JsonIgnore
    Set<Product> products;
}

