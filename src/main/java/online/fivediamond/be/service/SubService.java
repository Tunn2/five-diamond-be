package online.fivediamond.be.service;

import online.fivediamond.be.entity.Sub;
import online.fivediamond.be.enums.TypeOfSub;
import online.fivediamond.be.model.sub.SubCreationRequest;
import online.fivediamond.be.model.sub.SubUpdateRequest;
import online.fivediamond.be.repository.SubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubService {
    @Autowired
    SubRepository subRepository;

    public List<Sub> getAllSubs() {
        return subRepository.findAll();
    }

    public Sub getById(long id) {
        return subRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public void delete(long id) {
        subRepository.deleteById(id);
    }

    public Sub create(SubCreationRequest request) {
        Sub sub = new Sub();
        if (request.getSub().equals("DIAMOND")) {
            sub.setTypeOfSub(TypeOfSub.DIAMOND);
        } else if(request.getSub().equals("MOISSANITE")) {
            sub.setTypeOfSub(TypeOfSub.MOISSANITE);
        }

        sub.setPrice(request.getPrice());

        return subRepository.save(sub);
    }

    public Sub update(long id, SubUpdateRequest request) {
        Sub sub = subRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (request.getSub().equals("DIAMOND")) {
            sub.setTypeOfSub(TypeOfSub.DIAMOND);
        } else if(request.getSub().equals("MOISSANITE")) {
            sub.setTypeOfSub(TypeOfSub.MOISSANITE);
        }

        sub.setPrice(request.getPrice());

        return subRepository.save(sub);
    }
}
