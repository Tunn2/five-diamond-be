package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Diamond;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiamondRepository extends JpaRepository<Diamond, Long> {

    @Query(value = "SELECT * FROM diamond WHERE origin = :origin AND shape = :shape AND size = :size AND carat = :carat AND color = :color AND cut = :cut and clarity = :clarity and id not in (SELECT diamond_id FROM product)", nativeQuery = true)
    List<Diamond> getDiamonds(@Param("shape") String shape, @Param("origin") String origin, @Param("size") double size, @Param("carat") double carat, @Param("color") String color, @Param("cut") String cut, @Param("clarity") String clarity);
}
