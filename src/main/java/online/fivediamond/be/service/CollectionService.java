package online.fivediamond.be.service;

import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.model.collection.CollectionCreationRequest;
import online.fivediamond.be.model.collection.CollectionUpdateRequest;
import online.fivediamond.be.repository.CollectionRepository;
import online.fivediamond.be.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectionService {

    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    CollectionRepository collectionRepository;

    public Collection create(CollectionCreationRequest request) {
        Collection  collection = new Collection();
        collection.setName(request.getName());
        collection.setDescription(request.getDescription());
        collection.setImgURL(request.getImgURL());
        collection.setDeleted(false);
        collection = collectionRepository.save(collection);

        return collection;
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

    public Collection getById(long id) {
        return collectionRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
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
