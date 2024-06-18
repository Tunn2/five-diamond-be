package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.enums.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiamondRepository extends JpaRepository<Diamond, Long> {

    @Query(value = "SELECT * FROM diamond  WHERE shape LIKE '%string%' AND " +
            "size = 1 AND " +
            "carat = 0 AND " +
            "clarity LIKE '%string%' AND " +
            "cut LIKE '%string%' AND " +
            "color LIKE '%string%' AND " +
            "origin = 'NATURAL' AND " +
            "id NOT IN (SELECT diamond_id FROM product)", nativeQuery = true)
    List<Diamond> findByShapeContainingAndSizeAndCaratAndClarityContainingAndCutContainingAndColorContainingAndOrigin(String shape, double size, double carat, String clarity, String cut, String color, String origin);
}
