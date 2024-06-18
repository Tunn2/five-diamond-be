package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import online.fivediamond.be.enums.Origin;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Diamond {
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

    @OneToOne
    @JoinColumn(name = "certificate_id")
    @NotNull
    Certificate certificate;

    @JsonIgnore
    @OneToOne(mappedBy = "diamond")
    Product product;

}

