package online.fivediamond.be.service;

import online.fivediamond.be.entity.*;
import online.fivediamond.be.enums.GoldEnum;
import online.fivediamond.be.enums.TypeOfSub;
import online.fivediamond.be.model.productLine.ProductLineCreationRequest;
import online.fivediamond.be.model.productLine.ProductLineResponse;
import online.fivediamond.be.model.productLine.ProductLineUpdateRequest;
import online.fivediamond.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLineService {

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

    @Autowired
    GoldRepository goldRepository;

    @Autowired
    SubRepository subRepository;

    public ProductLine create(ProductLineCreationRequest request) {
        ProductLine productLine = new ProductLine();
        List<Diamond> diamonds = diamondRepository.findAllById(request.getDiamondID());
        Category category = categoryRepository.findById(request.getCategoryID()).orElseThrow();
        Gold gold = null;
        Sub sub = null;
        if (request.getKarat().equals("24K")) {
            gold = goldRepository.findByGoldEnum(GoldEnum.GOLD_24K);
        } else if (request.getKarat().equals("18K")) {
            gold = goldRepository.findByGoldEnum(GoldEnum.GOLD_18K);
        }

        if(request.getTypeOfSub().toString().equals("DIAMOND")) {
            sub = subRepository.findByTypeOfSub(TypeOfSub.DIAMOND);
        } else if(request.getTypeOfSub().toString().equals("MOISSANITE")) {
            sub = subRepository.findByTypeOfSub(TypeOfSub.MOISSANITE);
        }

        double priceOfMetal = (gold.getPricePerTael() * request.getWeight()) + (sub.getPrice() * request.getQuantityOfSub());
        double priceOfDiamond = diamonds.get(0).getPrice();

        double price = priceOfMetal + priceOfDiamond;
        productLine.setDescription(request.getDescription());
        productLine.setName(request.getName());
        productLine.setGender(request.getGender());
        productLine.setPrice(price);
        productLine.setPriceRate(request.getPriceRate());
        productLine.setFinalPrice(price + ((price * productLine.getPriceRate()) / 100));
        productLine.setMetal(request.getMetal());
        productLine.setKarat(request.getKarat());
        productLine.setWeight(request.getWeight());
        productLine.setCategory(category);
        productLine.setImgURL(request.getImgURL());
        productLine.setTypeOfSub(request.getTypeOfSub());
        productLine.setQuantityOfSub(request.getQuantityOfSub());
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
        Gold gold = null;
        Sub sub = null;
        if (request.getKarat().equals("24K")) {
            gold = goldRepository.findByGoldEnum(GoldEnum.GOLD_24K);
        } else if (request.getKarat().equals("18K")) {
            gold = goldRepository.findByGoldEnum(GoldEnum.GOLD_18K);
        }

        if(request.getTypeOfSub().toString().equals("DIAMOND")) {
            sub = subRepository.findByTypeOfSub(TypeOfSub.DIAMOND);
        } else if(request.getTypeOfSub().toString().equals("MOISSANITE")) {
            sub = subRepository.findByTypeOfSub(TypeOfSub.MOISSANITE);
        }
        for(Product product: products) {
            productRepository.deleteById(product.getId());
        }
        double priceOfMetal = (gold.getPricePerTael() * request.getWeight()) + (sub.getPrice() * request.getQuantityOfSub());
        double priceOfDiamond = diamonds.get(0).getPrice();

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
        List<ProductLine> list;
        list = productLineRepository.findAvailableProductLines();
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

    public ProductLineResponse getById(long id) {
        ProductLineResponse productLineResponse = new ProductLineResponse();
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<Long> diamondIds = new ArrayList<>();
        List<Product> products = productRepository.findByProductLineId(id);
        List<Collection> collections = collectionRepository.getCollectionByProductLineId(id);
        for(Product p : products) {
            diamondIds.add(p.getId());
        }
        productLineResponse.setCollectionList(collections);
        productLineResponse.setQuantity(productLine.getQuantity());
        productLineResponse.setId(productLine.getId());
        productLineResponse.setDiamondIds(diamondIds);
        productLineResponse.setCarat(productLine.getCarat());
        productLineResponse.setClarity(productLine.getClarity());
        productLineResponse.setCategory(productLine.getCategory());
        productLineResponse.setColor(productLine.getColor());
        productLineResponse.setCut(productLine.getCut());
        productLineResponse.setDeleted(productLine.isDeleted());
        productLineResponse.setGender(productLine.getGender());
        productLineResponse.setPriceRate(productLine.getPriceRate());
        productLineResponse.setSpecial(productLine.isSpecial());
        productLineResponse.setTypeOfSub(productLine.getTypeOfSub());
        productLineResponse.setQuantityOfSub(productLine.getQuantityOfSub());
        productLineResponse.setPriceRate(productLine.getPriceRate());
        productLineResponse.setFinalPrice(productLine.getFinalPrice());
        productLineResponse.setPrice(productLine.getPrice());
        productLineResponse.setWeight(productLine.getWeight());
        productLineResponse.setOrigin(productLine.getOrigin());
        productLineResponse.setName(productLine.getName());
        productLineResponse.setDescription(productLine.getDescription());
        productLineResponse.setImgURL(productLine.getImgURL());
        productLineResponse.setMetal(productLine.getMetal());
        productLineResponse.setKarat(productLine.getKarat());
        productLineResponse.setShape(productLine.getShape());
        return productLineResponse;
    }

    public List<ProductLine> search(String search) {
        return productLineRepository.findProductLineByName(search);
    }
}
