package online.fivediamond.be.repository;

import online.fivediamond.be.entity.DiamondPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiamondPriceRepository extends JpaRepository<DiamondPrice, Long> {
}
