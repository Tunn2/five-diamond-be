package online.fivediamond.be.service;

import online.fivediamond.be.entity.Material;
import online.fivediamond.be.entity.Product;
import online.fivediamond.be.model.product.ProductCreationRequest;
import online.fivediamond.be.model.product.ProductUpdateRequest;
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

    final double GOLD_24K = 7600000;
    final double GOLD_18K = GOLD_24K * 0.75;
    public Product createProduct(ProductCreationRequest request) {
        Product product = new Product();
        List<Material> materials = materialRepository.findAllById(request.getMaterialID());
        Set<Material> targetSet = new HashSet<>(materials);
        Iterator<Material> iterator = targetSet.iterator();
        double price = 0;
        double priceOfMetal = 0;
        double priceOfDiamond = 0;
        while(iterator.hasNext()) {
            Material material = iterator.next();
            if(material.getCertificate() == null) {
                if(material.getKarat().equals("24K"))
                    priceOfMetal = GOLD_24K * request.getWeight() + material.getPrice();
                else if(material.getKarat().equals("18K")) {
                    priceOfMetal = GOLD_18K * request.getWeight() + material.getPrice();
                }
            }else {
                priceOfDiamond = material.getPrice();
            }
        }
        price = priceOfMetal + priceOfDiamond;
        product.setGender(request.getGender());
        product.setPrice(price);
        product.setPriceRate(request.getPriceRate());
        product.setMaterials(targetSet);
        product.setCategory(categoryRepository.findById(request.getCategoryID()).orElseThrow(() -> new RuntimeException("Not found")));
        product.setImgURL(request.getImgURL());
        product.setWeight(request.getWeight());
        product.setSpecial(request.isSpecial());
        return productRepository.save(product);
    }

    public Product updateProductByID(long id, ProductUpdateRequest request) {
        Product product = getProductByID(id);

        List<Material> materials = materialRepository.findAllById(request.getMaterialID());
        Set<Material> targetSet = new HashSet<>(materials);
        Iterator<Material> iterator = targetSet.iterator();
        double price = 0;
        double priceOfMetal = 0;
        double priceOfDiamond = 0;
        while(iterator.hasNext()) {
            Material material = iterator.next();
            if(material.getCertificate() == null) {
                if(material.getKarat().equals("24K"))
                    priceOfMetal = GOLD_24K * request.getWeight() + material.getPrice();
                else if(material.getKarat().equals("18K")) {
                    priceOfMetal = GOLD_18K * request.getWeight() + material.getPrice();
                }
            }else {
                priceOfDiamond = material.getPrice();
            }
        }
        price = priceOfMetal + priceOfDiamond;
        product.setPrice(price);
        product.setGender(request.getGender());
        product.setPriceRate(request.getPriceRate());
        product.setMaterials(targetSet);
        product.setCategory(categoryRepository.findById(request.getCategoryID()).orElseThrow(() -> new RuntimeException("Not found")));
        product.setImgURL(request.getImgURL());
        product.setWeight(request.getWeight());
        product.setSpecial(request.isSpecial());
        return productRepository.save(product);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByIsDeletedFalseAndIsSaleFalse();
    }

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
