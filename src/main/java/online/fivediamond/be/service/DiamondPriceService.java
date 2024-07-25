package online.fivediamond.be.service;

import online.fivediamond.be.entity.DiamondPrice;
import online.fivediamond.be.model.diamondPrice.DiamondPriceCreationRequest;
import online.fivediamond.be.repository.DiamondPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DiamondPriceService {

    @Autowired
    DiamondPriceRepository diamondPriceRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

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

    @Scheduled(fixedRate = 10000)
    public void updateDiamondPricePrice() {
        Random random = new Random();
        double min = 0.99;
        double max = 1.01;
        double changePercentage = min + (max - min) * random.nextDouble();
        double roundedPercentage = Math.round(changePercentage * 1000.0) / 1000.0;
        diamondPriceRepository.updateDiamondPrice(roundedPercentage);
        simpMessagingTemplate.convertAndSend("/topic/price","price");
    }
}
