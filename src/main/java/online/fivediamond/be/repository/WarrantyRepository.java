package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Warranty findByProductId(long id);
}
