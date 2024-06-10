package online.fivediamond.be.model.certificate;

import lombok.Data;

import java.util.Date;

@Data
public class CertificateUpdateRequest {

    long giaReportNumber;
    String fileURL;
    Date dateOfIssues;
}
