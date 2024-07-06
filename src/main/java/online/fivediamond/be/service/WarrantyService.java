package online.fivediamond.be.service;

import online.fivediamond.be.entity.Warranty;
import online.fivediamond.be.repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyService {
    @Autowired
    WarrantyRepository warrantyRepository;

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    public Warranty getByProductId(long id) {
        return warrantyRepository.findByProductId(id);
    }
}
