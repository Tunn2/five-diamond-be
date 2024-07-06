package online.fivediamond.be.service;

import online.fivediamond.be.entity.*;
import online.fivediamond.be.model.productLine.ProductLineCreationRequest;
import online.fivediamond.be.model.productLine.ProductLineUpdateRequest;
import online.fivediamond.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLineService {
    final double GOLD_24K = 7600000;
    final double GOLD_18K = GOLD_24K * 0.75;
    final double SUB_DIAMOND = 500000;
    final double SUB_MOISSANITE = 250000;
    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    DiamondRepository diamondRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CollectionRepository collectionRepository;

    public ProductLine create(ProductLineCreationRequest request) {
        ProductLine productLine = new ProductLine();
        List<Diamond> diamonds = diamondRepository.findAllById(request.getDiamondID());
        Collection  collection = collectionRepository.findById(request.getCollectionID()).orElseThrow();;
        Category category = categoryRepository.findById(request.getCategoryID()).orElseThrow();
        double priceOfMetal = 0;
        double priceOfDiamond = diamonds.get(0).getPrice();
        if (request.getKarat().equals("24K")) {
            if (request.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_24K * request.getWeight() + request.getQuantityOfSub() * SUB_DIAMOND;
            } else if (request.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_24K * request.getWeight() + request.getQuantityOfSub() * SUB_MOISSANITE;
            }
        } else if (request.getKarat().equals("18K")) {
            if (request.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_18K * request.getWeight() + request.getQuantityOfSub() * SUB_DIAMOND;
            } else if (productLine.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_18K * request.getWeight() + request.getQuantityOfSub() * SUB_MOISSANITE;
            }
        }

        productLine.setCollection(collection);
        productLine.setDescription(request.getDescription());
        productLine.setName(request.getName());
        productLine.setGender(request.getGender());
        productLine.setPrice(priceOfMetal + priceOfDiamond);
        productLine.setPriceRate(request.getPriceRate());
        productLine.setFinalPrice(productLine.getPrice() + (productLine.getPrice() * productLine.getPriceRate()) / 100);
        productLine.setMetal(request.getMetal());
        productLine.setKarat(request.getKarat());
        productLine.setWeight(request.getWeight());
        productLine.setCategory(category);
        productLine.setImgURL(request.getImgURL());
        productLine.setTypeOfSub(request.getTypeOfSub());
        productLine.setQuantityOfSub(request.getQuantityOfSub());
        productLine.setQuantity(diamonds.size());
        productLine.setSpecial(request.isSpecial());
        productLine.setShape(request.getShape());
        productLine.setCut(request.getCut());
        productLine.setClarity(request.getClarity());
        productLine.setColor(request.getColor());
        productLine.setCarat(request.getCarat());
        productLine.setSize(request.getSize());
        productLine.setOrigin(request.getOrigin());
        productLine = productLineRepository.save(productLine);

        for (Diamond diamond : diamonds) {
            Product product = new Product();
            product.setDiamond(diamond);
            product.setProductLine(productLineRepository.findById(productLine.getId()).orElseThrow());
            productRepository.save(product);
        }
        return productLine;
    }

    public ProductLine update(long id, ProductLineUpdateRequest request) {
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<Diamond> diamonds = diamondRepository.findAllById(request.getDiamondID());
        List<Product> products = productRepository.findByProductLineId(id);
        for(Product product: products) {
            productRepository.deleteById(product.getId());
        }
        double priceOfMetal = 0;
        double priceOfDiamond = diamonds.get(0).getPrice();
        if(request.getKarat().equals("24K")) {
            if(request.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_24K * request.getWeight() + request.getQuantityOfSub() * SUB_DIAMOND;
            } else if (request.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_24K * request.getWeight() + request.getQuantityOfSub() * SUB_MOISSANITE;
            }
        } else if (request.getKarat().equals("18K")) {
            if(request.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_18K * request.getWeight() + request.getQuantityOfSub() * SUB_DIAMOND;
            } else if (productLine.getTypeOfSub().toString().equals("DIAMOND")) {
                priceOfMetal = GOLD_18K * request.getWeight() + request.getQuantityOfSub() * SUB_MOISSANITE;
            }
        }
        productLine.setDescription(request.getDescription());
        productLine.setName(request.getName());
        productLine.setGender(request.getGender());
        productLine.setPrice(priceOfMetal + priceOfDiamond);
        productLine.setPriceRate(request.getPriceRate());
        productLine.setMetal(request.getMetal());
        productLine.setKarat(request.getKarat());
        productLine.setWeight(request.getWeight());
        productLine.setCategory(categoryRepository.findById(request.getCategoryID()).orElseThrow());
        productLine.setImgURL(request.getImgURL());
        productLine.setTypeOfSub(request.getTypeOfSub());
        productLine.setQuantityOfSub(request.getQuantityOfSub());
        productLine.setQuantity(diamonds.size());
        productLine.setSpecial(request.isSpecial());
        productLine.setShape(request.getShape());
        productLine.setCut(request.getCut());
        productLine.setClarity(request.getClarity());
        productLine.setColor(request.getColor());
        productLine.setCarat(request.getCarat());
        productLine.setSize(request.getSize());
        productLine.setOrigin(request.getOrigin());
        productLine = productLineRepository.save(productLine);
        for (Diamond diamond : diamonds) {
            Product product = new Product();
            product.setDiamond(diamond);
            product.setProductLine(productLineRepository.findById(productLine.getId()).orElseThrow());
            productRepository.save(product);
        }
        return productLine;

    }

    public void deleteById(long id) {
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        productLine.setDeleted(true);
        productLineRepository.save(productLine);
    }

    public List<ProductLine> getAvailableProductLine() {
        List<ProductLine> list = productLineRepository.findAvailableProductLines();
        return list;
    }
    //mircoservice
//filter => controller => service => repo
    public List<ProductLine> getAllProductLines() {
        return productLineRepository.findAll();
    }

    public ProductLine delete(long id) {
        ProductLine productLine = productLineRepository.findById(id).orElseThrow();
        productLine.setDeleted(true);
        return productLineRepository.save(productLine);
    }

    public ProductLine getById(long id) {
        return productLineRepository.findById(id).orElseThrow();
    }

    public List<ProductLine> search(String search) {
        return productLineRepository.findProductLineByName(search);
    }
}
