package online.fivediamond.be.service;

import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.model.DiamondCreationRequest;
import online.fivediamond.be.model.DiamondUpdateRequest;
import online.fivediamond.be.repository.DiamondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiamondService {
    @Autowired
    DiamondRepository diamondRepository;

    public Diamond create(DiamondCreationRequest request) {
        Diamond diamond = new Diamond();

        diamond.setGiaReportNumber(request.getGiaReportNumber());
        diamond.setImgURL1(request.getImgURL1());
        diamond.setImgURL2(request.getImgURL2());
        diamond.setImgURL3(request.getImgURL3());
        diamond.setImgURL4(request.getImgURL4());
        diamond.setCost(request.getCost());
        diamond.setPrice(request.getPrice());
        diamond.setImportDate(request.getImportDate());
        diamond.setShape(request.getShape());
        diamond.setCarat(request.getCarat());
        diamond.setColor(request.getColor());
        diamond.setClarity(request.getClarity());
        diamond.setCut(request.getCut());
        diamond.setDateOfIssues(request.getDateOfIssues());

        return diamondRepository.save(diamond);
    }

    public List<Diamond> getAllDiamonds() {
        return diamondRepository.findAll();
    }

    public Diamond updateDiamondByID(long id, DiamondUpdateRequest request) {
        Diamond diamond = getDiamondByID(id);

        diamond.setGiaReportNumber(request.getGiaReportNumber());
        diamond.setImgURL1(request.getImgURL1());
        diamond.setImgURL2(request.getImgURL2());
        diamond.setImgURL3(request.getImgURL3());
        diamond.setImgURL4(request.getImgURL4());
        diamond.setCost(request.getCost());
        diamond.setPrice(request.getPrice());
        diamond.setImportDate(request.getImportDate());
        diamond.setShape(request.getShape());
        diamond.setCarat(request.getCarat());
        diamond.setColor(request.getColor());
        diamond.setClarity(request.getClarity());
        diamond.setCut(request.getCut());
        diamond.setDateOfIssues(request.getDateOfIssues());

        return diamondRepository.save(diamond);
    }

    public void deleteDiamondByID(long id) {
        diamondRepository.deleteById(id);
    }

    public Diamond getDiamondByID(long id) {
        return diamondRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
