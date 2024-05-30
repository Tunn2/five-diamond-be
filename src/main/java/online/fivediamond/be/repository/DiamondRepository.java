package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Diamond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiamondRepository extends JpaRepository<Diamond, Long> {
}
