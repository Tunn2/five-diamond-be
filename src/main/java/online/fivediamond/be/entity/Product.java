package online.fivediamond.be.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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

    @JsonIgnore
    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    OrderItem orderItem;

    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    Warranty warranty;
}
