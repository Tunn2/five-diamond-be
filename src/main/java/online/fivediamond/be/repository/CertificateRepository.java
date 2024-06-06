package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findByGiaReportNumber(long giaReportNumber);
}
