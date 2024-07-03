package online.fivediamond.be.service;

import online.fivediamond.be.entity.Promotion;
import online.fivediamond.be.model.promotion.PromotionCreationRequest;
import online.fivediamond.be.repository.PromotionRepository;
import online.fivediamond.be.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    DateUtils dateUtils;

    public Promotion create(PromotionCreationRequest request) {
        Promotion promotion = new Promotion();
        promotion.setCode(request.getCode());
        promotion.setDiscountPercentage(request.getDiscountPercentage());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setDeleted(false);
        return promotionRepository.save(promotion);
    }

    public Promotion getPromotionById(long id) {
        return promotionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll();
    }

    public Promotion deletePromotionById(long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        promotion.setDeleted(true);
        return promotionRepository.save(promotion);
    }

    public Promotion getPromotionByCode(String code) {
        Promotion promotion = promotionRepository.findByCode(code);
        if (promotion == null) {
            throw new RuntimeException("Not found");
        }
        if(dateUtils.isExpired(promotion.getEndDate())) {
            throw new RuntimeException("Expired");
        }

        if(dateUtils.isNotYet(promotion.getStartDate())) {
            throw new RuntimeException("Not yet start");
        }

        return promotion;
    }

}
