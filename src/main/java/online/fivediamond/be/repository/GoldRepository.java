package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Gold;
import online.fivediamond.be.enums.GoldEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface GoldRepository extends JpaRepository<Gold, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE gold SET price_per_tael = price_per_tael * :price WHERE id > 0", nativeQuery = true)
    void updatePrice(@Param("price") double price);
    Gold findByGoldEnum(GoldEnum goldEnum);
}
