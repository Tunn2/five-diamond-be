package online.fivediamond.be.service;

import online.fivediamond.be.entity.Material;
import online.fivediamond.be.entity.Product;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.model.ProductLineCreationRequest;
import online.fivediamond.be.repository.ProductLineRepository;
import online.fivediamond.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductLineService {
    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductLine create(ProductLineCreationRequest request) {
        ProductLine productLine = new ProductLine();
        List<Product> products = productRepository.findAllById(request.getProductID());
        Set<Product> productSet = new HashSet<>(products);

        productLine.setName(request.getName());
        productLine.setDescription(request.getDescription());
        productLine.setProducts(productSet);

        return productLineRepository.save(productLine);
    }

    

}
