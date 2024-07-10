package online.fivediamond.be.service;

import online.fivediamond.be.entity.Certificate;
import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.model.diamond.DiamondCreationRequest;
import online.fivediamond.be.model.diamond.DiamondUpdateRequest;
import online.fivediamond.be.repository.CertificateRepository;
import online.fivediamond.be.repository.DiamondRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiamondService {
    private static final Logger log = LoggerFactory.getLogger(DiamondService.class);
    @Autowired
    DiamondRepository diamondRepository;

    @Autowired
    CertificateRepository certificateRepository;

    public Diamond create(DiamondCreationRequest request) {
        Diamond diamond = new Diamond();
        Certificate certificate = certificateRepository.findById(request.getCertificateID()).orElseThrow(() -> new RuntimeException("Not found"));
        diamond.setCertificate(certificate);
        diamond.setShape(request.getShape());
        diamond.setSize(request.getSize());
        diamond.setColor(request.getColor());
        diamond.setClarity(request.getClarity());
        diamond.setCarat(request.getCarat());
        diamond.setCut(request.getCut());
        diamond.setOrigin(request.getOrigin());
        diamond.setImgURL(request.getImgURL());
        diamond.setPrice(request.getPrice());
        return diamondRepository.save(diamond);
    }

    public Diamond update(long id, DiamondUpdateRequest request) {
        Diamond diamond = getDiamondByID(id);
        Certificate certificate = null;
        try {
            certificate = certificateRepository.findByGiaReportNumber(request.getGiaReportNumber());
        }catch (Exception ex) {
            log.error(ex.getMessage());
        }
        diamond.setCertificate(certificate);
        diamond.setShape(request.getShape());
        diamond.setSize(request.getSize());
        diamond.setColor(request.getColor());
        diamond.setClarity(request.getClarity());
        diamond.setCarat(request.getCarat());
        diamond.setCut(request.getCut());
        diamond.setOrigin(request.getOrigin());
        diamond.setImgURL(request.getImgURL());
        diamond.setPrice(request.getPrice());
        return diamondRepository.save(diamond);
    }

    public void delete(long id) {
        diamondRepository.deleteById(id);
    }

    public List<Diamond> getAllDiamonds() {
        return diamondRepository.findAll();
    }


    public Diamond getDiamondByID(long id) {
        return diamondRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Diamond> getDiamonds(String shape, String origin, double size, double carat, String color, String cut, String clarity) {
        return diamondRepository.getDiamonds(shape, origin, size, carat, color, cut, clarity);
    }

    public List<Diamond> getDiamonds1(String shape, String origin, double size, double carat, String color, String cut, String clarity) {
        return diamondRepository.getDiamonds(shape, origin, size, carat, color, cut, clarity);
    }


}
