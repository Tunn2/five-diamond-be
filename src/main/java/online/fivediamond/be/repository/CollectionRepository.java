package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findByIsDeletedFalse();

    @Query(value = "SELECT c.* FROM collection_productline p JOIN collection c ON c.id = p.collection_id where p.product_line_id = :productLineId", nativeQuery = true)
    List<Collection> getCollectionByProductLineId(@Param("productLineId") long productLineId);
}
