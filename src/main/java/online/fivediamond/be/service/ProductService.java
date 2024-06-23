package online.fivediamond.be.service;

import online.fivediamond.be.entity.Product;
import online.fivediamond.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void deleteProductByID(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductNotYetSale() {
        return productRepository.findByIsSaleFalse();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByID(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
