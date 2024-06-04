package online.fivediamond.be.service;

import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.entity.Material;
import online.fivediamond.be.model.MaterialCreationRequest;
import online.fivediamond.be.model.MaterialUpdateRequest;
import online.fivediamond.be.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    MaterialRepository materialRepository;

    public Material create(MaterialCreationRequest request) {
        Material material = new Material();
        material.setGiaReportNumber(request.getGiaReportNumber());
        material.setShape(request.getShape());
        material.setSize(request.getSize());
        material.setColor(request.getColor());
        material.setClarity(request.getClarity());
        material.setCarat(request.getCarat());
        material.setCut(request.getCut());
        material.setImgURL(request.getImgURL());
        material.setType(request.getType());
        material.setType(request.getType());
        material.setMetal(request.getMetal());
        material.setKarat(request.getKarat());
        material.setWeight(request.getWeight());
        material.setQuantityOfSub(request.getQuantityOfSub());
        material.setQuantity(request.getQuantity());
        material.setPrice(request.getPrice());
        return materialRepository.save(material);
    }

    public Material update(long id, MaterialUpdateRequest request) {
        Material material = getMaterialByID(id);
        material.setGiaReportNumber(request.getGiaReportNumber());
        material.setShape(request.getShape());
        material.setSize(request.getSize());
        material.setColor(request.getColor());
        material.setClarity(request.getClarity());
        material.setCarat(request.getCarat());
        material.setCut(request.getCut());
        material.setImgURL(request.getImgURL());
        material.setType(request.getType());
        material.setType(request.getType());
        material.setMetal(request.getMetal());
        material.setKarat(request.getKarat());
        material.setWeight(request.getWeight());
        material.setQuantityOfSub(request.getQuantityOfSub());
        material.setQuantity(request.getQuantity());
        material.setPrice(request.getPrice());
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
}
