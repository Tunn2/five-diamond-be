package online.fivediamond.be.service;

import online.fivediamond.be.entity.DiamondPrice;
import online.fivediamond.be.model.diamondPrice.DiamondPriceCreationRequest;
import online.fivediamond.be.repository.DiamondPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiamondPriceService {

    @Autowired
    DiamondPriceRepository diamondPriceRepository;

    public List<DiamondPrice> get() {
        return diamondPriceRepository.findAll();
    }

    public DiamondPrice create(DiamondPriceCreationRequest request) {
        DiamondPrice diamondPrice = new DiamondPrice();
        diamondPrice.setSize(request.getSize());
        diamondPrice.setColor(request.getColor());
        diamondPrice.setClarity(request.getClarity());
        diamondPrice.setPrice(request.getPrice());
        return diamondPriceRepository.save(diamondPrice);
    }
}
