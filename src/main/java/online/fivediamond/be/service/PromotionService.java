package online.fivediamond.be.service;

import online.fivediamond.be.entity.Promotion;
import online.fivediamond.be.model.promotion.PromotionCreationRequest;
import online.fivediamond.be.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    public Promotion create(PromotionCreationRequest request) {
        Promotion promotion = new Promotion();
        promotion.setCode(request.getCode());
        promotion.setDiscountPercentage(request.getDiscountPercentage());
        promotion.setQuantity(request.getQuantity());
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

    public Promotion getPromotionByCode(String code) throws ParseException {
        Promotion promotion = promotionRepository.findByCode(code);
        String startDateStr = promotion.getStartDate();
        String endDateStr= promotion.getEndDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);
        if (startDate.compareTo(endDate) >= 0)
            return promotion;
        return null;
    }
}
