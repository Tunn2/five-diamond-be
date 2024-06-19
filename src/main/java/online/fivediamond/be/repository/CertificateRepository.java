package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findByGiaReportNumber(long giaReportNumber);

    @Query(value = "SELECT * FROM certificate WHERE id NOT IN (SELECT certificate_id FROM diamond)", nativeQuery = true)
    List<Certificate> findByNotYetUsed();
}
