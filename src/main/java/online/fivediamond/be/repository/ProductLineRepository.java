package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.model.dto.BestSeller;
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
    @Query(value = "SELECT p.product_line_id AS productLineId, COUNT(*) AS quantity " +
            "FROM product p " +
            "JOIN order_item o ON p.id = o.product_id " +
            "JOIN orders od ON o.order_id = od.id " +
            "WHERE MONTH(od.order_date) = :month AND YEAR(od.order_date) = :year " +
            "GROUP BY p.product_line_id " +
            "ORDER BY quantity DESC", nativeQuery = true)
    List<Object[]> getBestSellersByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT p.* FROM collection_productline c JOIN product_line p ON c.product_line_id = p.id where c.collection_id = :collectionId", nativeQuery = true)
    List<ProductLine> getProductLineByCollectionId(@Param("collectionId") long collectionId);
}
