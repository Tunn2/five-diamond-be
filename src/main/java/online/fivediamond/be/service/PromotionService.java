package online.fivediamond.be.service;

import online.fivediamond.be.entity.Promotion;
import online.fivediamond.be.model.promotion.PromotionCreationRequest;
import online.fivediamond.be.repository.PromotionRepository;
import online.fivediamond.be.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        List<Promotion> promotions = promotionRepository.findByDiscountPercentage(request.getDiscountPercentage());

        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());

        for (Promotion existingPromotion : promotions) {
            if (isOverlap(existingPromotion.getStartDate(), existingPromotion.getEndDate(),
                    promotion.getStartDate(), promotion.getEndDate())) {
                throw new RuntimeException("Mã giảm giá này bị overlap");
            }
        }
        promotion.setDeleted(false);
        return promotionRepository.save(promotion);
    }

    public boolean isOverlap(String start1, String end1, String start2, String end2) {
        String pattern = "yyyy-MM-dd";
        LocalDate startDate1 = convertStringToDate(start1, pattern);
        LocalDate endDate1 = convertStringToDate(end1, pattern);
        LocalDate startDate2 = convertStringToDate(start2, pattern);
        LocalDate endDate2 = convertStringToDate(end2, pattern);

        if (startDate1 == null || endDate1 == null || startDate2 == null || endDate2 == null) {
            throw new RuntimeException("Lỗi định dạng ngày");
        }

        return (startDate1.isBefore(endDate2) || startDate1.equals(endDate2))
                && (startDate2.isBefore(endDate1) || startDate2.equals(endDate1));
    }

    public static LocalDate convertStringToDate(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi định dạng ngày: " + e.getMessage());
            return null;
        }
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
