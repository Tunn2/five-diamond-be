package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Gold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoldRepository extends JpaRepository<Gold, Long> {
    Gold findByGoldEnum(String gold);
}
