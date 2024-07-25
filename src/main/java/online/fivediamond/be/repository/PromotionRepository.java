package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByCode(String code);

    @Query(value = "SELECT * FROM promotion where discount_percentage = :promotion", nativeQuery = truegit)
    List<Promotion> findByDiscountPercentage(@Param("promotion") double discountPercentage);
}
