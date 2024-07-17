package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Sub;
import online.fivediamond.be.enums.TypeOfSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepository extends JpaRepository<Sub, Long> {
    Sub findByTypeOfSub(TypeOfSub typeOfSub);
}
