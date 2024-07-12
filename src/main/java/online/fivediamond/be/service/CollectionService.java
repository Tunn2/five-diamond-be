package online.fivediamond.be.service;

import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.model.collection.CollectionCreationRequest;
import online.fivediamond.be.model.collection.CollectionResponse;
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
        List<ProductLine> productLines = productLineRepository.findAllById(request.getProductLineIds());
        Set<ProductLine> productLineSet = new HashSet<>(productLines);
        collection.setName(request.getName());
        collection.setDescription(request.getDescription());
        collection.setImgURL(request.getImgURL());
        collection.setDeleted(false);
        collection.setProductLines(productLineSet);
        return collectionRepository.save(collection);
    }

    public Collection update(long id, CollectionUpdateRequest request) {
        Collection collection = collectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        collection.setName(request.getName());
        collection.setDescription(request.getDescription());
        collection.setImgURL(request.getImgURL());
        return collectionRepository.save(collection);
    }

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public CollectionResponse getById(long id) {
        CollectionResponse collectionResponse = new CollectionResponse();
        Collection collection = collectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<ProductLine> productLines = productLineRepository.getProductLineByCollectionId(id);

        collectionResponse.setProductLinesList(productLines);
        collectionResponse.setId(collection.getId());
        collectionResponse.setName(collection.getName());
        collectionResponse.setDescription(collection.getDescription());
        collectionResponse.setDeleted(collection.isDeleted());
        collectionResponse.setImgURL(collection.getImgURL());

        return collectionResponse;
    }

    public Collection delete(long id) {
        Collection collection = collectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        collection.setDeleted(true);
        return collectionRepository.save(collection);
    }

    public List<Collection> getAvailableCollections() {
        return collectionRepository.findByIsDeletedFalse();
    }
}
