package online.fivediamond.be.repository;

import online.fivediamond.be.entity.CanceledOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelOrderRepository extends JpaRepository<CanceledOrder, Long> {
}
