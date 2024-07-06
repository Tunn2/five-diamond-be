package online.fivediamond.be.repository;

import online.fivediamond.be.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {

    @Query(value = "SELECT * FROM product_line WHERE is_deleted = false AND quantity > 0", nativeQuery = true)
    List<ProductLine> findAvailableProductLines();

    @Query(value = "SELECT * FROM product_line WHERE name LIKE CONCAT('%', :search, '%') AND is_deleted = false AND quantity > 0", nativeQuery = true)
    List<ProductLine> findProductLineByName(@Param("search") String search);
}
