package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.enums.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiamondRepository extends JpaRepository<Diamond, Long> {
    List<Diamond> findByShapeContainingAndSizeAndCaratAndClarityContainingAndCutContainingAndColorContainingAndOrigin(
            String shape, double size, double carat, String clarity, String cut, String color, Origin origin);
}
