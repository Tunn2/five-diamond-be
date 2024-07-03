package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Order;
import online.fivediamond.be.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(long id);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
