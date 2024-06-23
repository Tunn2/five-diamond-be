package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductLineId(long id);
    List<Product> findByIsSaleFalse();

    @Query(value = "SELECT p.* " +
            "FROM product p " +
            "JOIN product_line pl " +
            "ON p.product_line_id = pl.id " +
            "WHERE is_sale = 0 " +
            "AND pl.id = :plId " +
            "LIMIT :quantity", nativeQuery = true)
    List<Product> findAvailableProducts(@Param("quantity") int quantity, @Param("plId") long plId);
}
