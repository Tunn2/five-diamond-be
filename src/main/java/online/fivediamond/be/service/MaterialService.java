package online.fivediamond.be.service;

import online.fivediamond.be.entity.Certificate;
import online.fivediamond.be.entity.Material;
import online.fivediamond.be.enums.Type;
import online.fivediamond.be.enums.TypeOfSub;
import online.fivediamond.be.model.material.MaterialCreationRequest;
import online.fivediamond.be.model.material.MaterialUpdateRequest;
import online.fivediamond.be.repository.CertificateRepository;
import online.fivediamond.be.repository.MaterialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    private static final Logger log = LoggerFactory.getLogger(MaterialService.class);
    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    CertificateRepository certificateRepository;

    final double SUB_DIAMOND = 500000;
    final double SUB_MOISSANITE = 250000;

    public Material create(MaterialCreationRequest request) {
        Material material = new Material();
        Certificate certificate = null;
        try {
             certificate = certificateRepository.findByGiaReportNumber(request.getGiaReportNumber());
        }catch (Exception ex) {
            log.error(ex.getMessage());
        }

        if(request.getType().toString().equals(Type.DIAMOND.toString())) {
            material.setCertificate(certificate);
            material.setShape(request.getShape());
            material.setSize(request.getSize());
            material.setColor(request.getColor());
            material.setClarity(request.getClarity());
            material.setCarat(request.getCarat());
            material.setCut(request.getCut());
            material.setOrigin(request.getOrigin());
            material.setImgURL(request.getImgURL());
            material.setPrice(request.getPrice());
            material.setType(request.getType());
        } else {
            material.setType(request.getType());
            material.setMetal(request.getMetal());
            material.setKarat(request.getKarat());
            material.setTypeOfSub(request.getTypeOfSub());
            if(request.getTypeOfSub().toString().equals(TypeOfSub.DIAMOND.toString())) {
                material.setPrice(SUB_DIAMOND * request.getQuantityOfSub());
            } else if (request.getTypeOfSub().toString().equals(TypeOfSub.MOISSANITE.toString())) {
                material.setPrice(SUB_MOISSANITE * request.getQuantityOfSub());
            }
            material.setTypeOfSub(request.getTypeOfSub());
            material.setQuantityOfSub(request.getQuantityOfSub());
            material.setCaratOfSub(request.getCaratOfSub());
        }
        return materialRepository.save(material);
    }

    public Material update(long id, MaterialUpdateRequest request) {
        Material material = getMaterialByID(id);
        material.setShape(request.getShape());
        material.setSize(request.getSize());
        material.setColor(request.getColor());
        material.setClarity(request.getClarity());
        material.setCarat(request.getCarat());
        material.setCut(request.getCut());
        material.setOrigin(request.getOrigin());
        material.setImgURL(request.getImgURL());
        material.setType(request.getType());
        material.setMetal(request.getMetal());
        material.setKarat(request.getKarat());
        material.setPrice(request.getPrice());
        material.setTypeOfSub(request.getTypeOfSub());
        material.setQuantityOfSub(request.getQuantityOfSub());
        material.setCaratOfSub(request.getCaratOfSub());
        return materialRepository.save(material);
    }

    public void delete(long id) {
        materialRepository.deleteById(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }


    public Material getMaterialByID(long id) {
        return materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Material> getDiamondsNotYetUsed() {
        return materialRepository.findByNotYetUsed();
    }

    public List<Material> getAllCovers() {
        return materialRepository.findByType(Type.COVER);
    }

}
