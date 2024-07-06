//package online.fivediamond.be.repository;
//
//import online.fivediamond.be.model.dto.BestSellerProductLine;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public interface BestSellerRepository extends JpaRepository<BestSellerProductLine, Long> {
//    @Query(value = "SELECT p.product_line_id, COUNT(*) as quantity FROM product p join order_item o join orders od on p.id = o.product_id AND o.order_id = od.id where MONTH(od.order_date) = :month and YEAR(od.order_date) = :year GROUP BY p.product_line_id order by quantity desc", nativeQuery = true)
//    List<BestSellerProductLine> getBestSellerByMonth(@Param("month") int month, @Param("year") int year);
//}
