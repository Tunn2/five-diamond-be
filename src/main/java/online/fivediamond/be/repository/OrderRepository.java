package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.Order;
import online.fivediamond.be.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(long id);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    @Query(value = "SELECT COALESCE(SUM(o.total_amount), 0) FROM orders o WHERE MONTH(o.order_date) = :month AND YEAR(o.order_date) = :year AND o.order_status != 'CANCELED'", nativeQuery = true)
    Double getRevenueByMonth(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT * FROM orders WHERE MONTH(order_date) = :month AND YEAR(order_date) = :year", nativeQuery = true)
    List<Order> findOrderByOrderDate(@Param("month") int month, @Param("year") int year);



}
