package online.fivediamond.be.repository;

import online.fivediamond.be.entity.DiamondPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiamondPriceRepository extends JpaRepository<DiamondPrice, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE diamond_price SET price = ROUND(price * :price, -3) WHERE id > 0", nativeQuery = true)
    void updateDiamondPrice(@Param("price") double price);
}
