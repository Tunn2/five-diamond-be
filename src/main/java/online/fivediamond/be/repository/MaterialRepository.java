package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Material;
import online.fivediamond.be.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query(value = "SELECT * FROM material where type = 'DIAMOND' AND id NOT IN (select material_id FROM product_material)", nativeQuery = true)
    List<Material> findByNotYetUsed();

}
