package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Sub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepository extends JpaRepository<Sub, Long> {
    Sub findByTypeOfSub(String typeOfSub);
}
