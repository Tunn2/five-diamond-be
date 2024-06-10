package online.fivediamond.be.service;

import online.fivediamond.be.entity.Certificate;
import online.fivediamond.be.model.certificate.CertificateCreationRequest;
import online.fivediamond.be.model.certificate.CertificateUpdateRequest;
import online.fivediamond.be.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    public Certificate create(CertificateCreationRequest request) {
        Certificate certificate = new Certificate();
        certificate.setGiaReportNumber(request.getGiaReportNumber());
        certificate.setFileURL(request.getFileURL());
        certificate.setDateOfIssues(request.getDateOfIssues());
        return certificateRepository.save(certificate);
    }

    public void delete(long id) {
        certificateRepository.deleteById(id);
    }

    public Certificate update(long id, CertificateUpdateRequest request) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        certificate.setGiaReportNumber(request.getGiaReportNumber());
        certificate.setFileURL(request.getFileURL());
        certificate.setDateOfIssues(request.getDateOfIssues());
        return certificateRepository.save(certificate);
    }

    public List<Certificate> getAllCertificate() {
        return certificateRepository.findAll();
    }

    public Certificate getByGIANumber(long giaReportNumber) {
        return certificateRepository.findByGiaReportNumber(giaReportNumber);
    }

    public List<Certificate> getCertificatesNotYetUsed(){
        return certificateRepository.findByNotYetUsed();
    }


}
