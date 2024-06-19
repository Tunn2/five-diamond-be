package online.fivediamond.be.api;

import online.fivediamond.be.entity.Certificate;
import online.fivediamond.be.model.certificate.CertificateCreationRequest;
import online.fivediamond.be.model.certificate.CertificateUpdateRequest;
import online.fivediamond.be.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/certificate")
public class CertificateAPI {

    @Autowired
    CertificateService certificateService;

    @GetMapping
    public ResponseEntity getAllCertificate() {
        return ResponseEntity.ok(certificateService.getAllCertificate());
    }

    @GetMapping("{id}")
    public ResponseEntity getByGiaNumber(@PathVariable long id) {
        Certificate certificate = certificateService.getByGIANumber(id);
        return ResponseEntity.ok(certificate);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CertificateCreationRequest request) {
        return ResponseEntity.ok(certificateService.create(request));
    }

    @DeleteMapping("{id}")
    public String delete (@PathVariable long id) {
        certificateService.delete(id);
        return "Delete successfully";
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody CertificateUpdateRequest request) {
        return ResponseEntity.ok(certificateService.update(id, request));
    }

    @GetMapping("available")
    public ResponseEntity getCertificatesNotYetUsed() {
        return ResponseEntity.ok(certificateService.getCertificatesNotYetUsed());
    }

}
