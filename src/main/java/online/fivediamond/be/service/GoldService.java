package online.fivediamond.be.service;

import online.fivediamond.be.entity.Gold;
import online.fivediamond.be.enums.GoldEnum;
import online.fivediamond.be.model.gold.GoldCreationRequest;
import online.fivediamond.be.model.gold.GoldUpdateRequest;
import online.fivediamond.be.repository.GoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GoldService {
    @Autowired
    GoldRepository goldRepository;

    public List<Gold> getAllGolds() {
        return goldRepository.findAll();
    }

    public Gold create(GoldCreationRequest request) {
        Gold gold = new Gold();

        if (request.getGold().equals("24K")) {
            gold.setGoldEnum(GoldEnum.GOLD_24K);
        }
        else if (request.getGold().equals("18K")) {
            gold.setGoldEnum(GoldEnum.GOLD_18K);
        }
        gold.setPricePerTael(request.getPricePerTael());

        return goldRepository.save(gold);
    }

    public Gold update(long id, GoldUpdateRequest request) {
        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (request.getGold().equals("24K")) {
            gold.setGoldEnum(GoldEnum.GOLD_24K);
        }
        else if (request.getGold().equals("18K")) {
            gold.setGoldEnum(GoldEnum.GOLD_18K);
        }
        gold.setPricePerTael(request.getPricePerTael());

        return goldRepository.save(gold);
    }

    public void delete(long id) {
        goldRepository.deleteById(id);
    }

    public Gold getById(long id) {
        return goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Scheduled(fixedRate = 100000)
    public void updatePrice() {
        Random random = new Random();
        double changePercentage = 1 + (random.nextDouble() * 0.2 - 0.1);
        double roundedPercentage = Math.round(changePercentage * 100.0) / 100.0;// Tạo giá trị trong khoảng 0.9 đến 1.1
        System.out.println(roundedPercentage);
        goldRepository.updatePrice(roundedPercentage);
    }
}
