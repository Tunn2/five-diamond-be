package online.fivediamond.be.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import online.fivediamond.be.enums.Gender;
import org.checkerframework.common.aliasing.qual.Unique;

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

    boolean isSale = false;

    @OneToOne
    @JoinColumn(name = "diamond_id")
    Diamond diamond;

    @ManyToOne
    @JoinColumn(name = "productLine_id")
    ProductLine productLine;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    OrderItem orderItem;
}
