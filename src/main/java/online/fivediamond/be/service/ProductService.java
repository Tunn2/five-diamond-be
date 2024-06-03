package online.fivediamond.be.service;

import online.fivediamond.be.entity.Product;
import online.fivediamond.be.model.ProductCreationRequest;
import online.fivediamond.be.model.ProductUpdateRequest;
import online.fivediamond.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public Product createProduct(ProductCreationRequest request) {
        Product product = new Product();

        product.setType(request.getType());
        product.setMetal(request.getMetal());
        product.setKarat(request.getKarat());
        product.setGiaReportNumber(request.getGiaReportNumber());
        product.setImgURL1(request.getImgURL1());
        product.setImgURL2(request.getImgURL2());
        product.setImgURL3(request.getImgURL3());
        product.setImgURL4(request.getImgURL4());
        product.setCost(request.getCost());
        product.setPrice(request.getPrice());
        product.setImportDate(request.getImportDate());
        product.setShape(request.getShape());
        product.setCarat(request.getCarat());
        product.setColor(request.getColor());
        product.setClarity(request.getClarity());
        product.setCut(request.getCut());
        product.setDateOfIssues(request.getDateOfIssues());

        return productRepository.save(product);
    }

    public Product updateProductByID(long id, ProductUpdateRequest request) {
        Product product = getProductByID(id);

        product.setGiaReportNumber(request.getGiaReportNumber());
        product.setImgURL1(request.getImgURL1());
        product.setImgURL2(request.getImgURL2());
        product.setImgURL3(request.getImgURL3());
        product.setImgURL4(request.getImgURL4());
        product.setCost(request.getCost());
        product.setPrice(request.getPrice());
        product.setType(request.getType());
        product.setMetal(request.getMetal());
        product.setKarat(request.getKarat());
        product.setImportDate(request.getImportDate());
        product.setShape(request.getShape());
        product.setCarat(request.getCarat());
        product.setColor(request.getColor());
        product.setClarity(request.getClarity());
        product.setCut(request.getCut());

        product.setDateOfIssues(request.getDateOfIssues());
        return productRepository.save(product);
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
