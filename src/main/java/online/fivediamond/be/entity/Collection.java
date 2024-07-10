package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String description;
    String imgURL;

    boolean isDeleted;
    @JsonIgnore
    @OneToMany(mappedBy = "collection")
    Set<ProductLine> productLines;
}
