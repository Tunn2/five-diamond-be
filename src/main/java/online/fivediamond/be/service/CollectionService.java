package online.fivediamond.be.service;

import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.model.collection.CollectionCreationRequest;
import online.fivediamond.be.model.collection.CollectionUpdateRequest;
import online.fivediamond.be.repository.CollectionRepository;
import online.fivediamond.be.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CollectionService {

    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    CollectionRepository collectionRepository;

    public Collection create(CollectionCreationRequest request) {
        Collection  collection = new Collection();
        List<ProductLine> productLines = productLineRepository.findAllById(request.getProductLineId());
        Set<ProductLine> productLineSet = new HashSet<>(productLines);
        collection.setName(request.getName());
        collection.setDescription(request.getDescription());
        collection.setImgURL(request.getImgURL());
        collection.setProductLines(productLineSet);
        collection = collectionRepository.save(collection);
        for(ProductLine productLine: productLines) {
            ProductLine productLine1 = productLineRepository.findById(productLine.getId()).orElseThrow(() -> new RuntimeException("Not found"));
            productLine1.setCollection(collection);
            productLineRepository.save(productLine1);
        }
        return collection;
    }

    public Collection update(long id, CollectionUpdateRequest request) {
        Collection collection = collectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<ProductLine> productLines = productLineRepository.findAllById(request.getProductLineId());
        Set<ProductLine> productLineSet = new HashSet<>(productLines);
        collection.setName(request.getName());
        collection.setDescription(request.getDescription());
        collection.setImgURL(request.getImgURL());
        collection.setProductLines(productLineSet);
        return collectionRepository.save(collection);
    }

}
