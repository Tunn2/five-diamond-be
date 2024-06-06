package online.fivediamond.be.service;

import online.fivediamond.be.entity.Material;
import online.fivediamond.be.entity.Product;
import online.fivediamond.be.model.ProductCreationRequest;
import online.fivediamond.be.model.ProductUpdateRequest;
import online.fivediamond.be.repository.CategoryRepository;
import online.fivediamond.be.repository.MaterialRepository;
import online.fivediamond.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product createProduct(ProductCreationRequest request) {
        Product product = new Product();
        List<Material> materials = materialRepository.findAllById(request.getMaterialID());
        Set<Material> targetSet = new HashSet<>(materials);
        Iterator<Material> iterator = targetSet.iterator();
        double price = 0;
        while(iterator.hasNext()) {
            Material material = iterator.next();
            price += material.getPrice();
        }
        product.setPrice(price);
        product.setPriceRate(request.getPriceRate());
        product.setMaterials(targetSet);
        product.setCategory(categoryRepository.findById(request.getCategoryID()).orElseThrow(() -> new RuntimeException("Not found")));
        product.setImgURL(request.getImgURL());
        product.setWeight(request.getWeight());
        return productRepository.save(product);
    }

//    public Product updateProductByID(long id, ProductUpdateRequest request) {
//        Product product = getProductByID(id);
//
//        product.setGiaReportNumber(request.getGiaReportNumber());
//        product.setImgURL1(request.getImgURL1());
//        product.setImgURL2(request.getImgURL2());
//        product.setImgURL3(request.getImgURL3());
//        product.setImgURL4(request.getImgURL4());
//        product.setCost(request.getCost());
//        product.setPrice(request.getPrice());
//        product.setType(request.getType());
//        product.setMetal(request.getMetal());
//        product.setKarat(request.getKarat());
//        product.setImportDate(request.getImportDate());
//        product.setShape(request.getShape());
//        product.setCarat(request.getCarat());
//        product.setColor(request.getColor());
//        product.setClarity(request.getClarity());
//        product.setCut(request.getCut());
//        product.setDateOfIssues(request.getDateOfIssues());
//        return productRepository.save(product);
//    }
//
    public void deleteProductByID(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByID(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
