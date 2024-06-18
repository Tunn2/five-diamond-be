//package online.fivediamond.be.service;
//
//import online.fivediamond.be.entity.Account;
//import online.fivediamond.be.entity.Cart;
//import online.fivediamond.be.entity.Product;
//import online.fivediamond.be.repository.AuthenticationRepository;
//import online.fivediamond.be.repository.CartRepository;
//import online.fivediamond.be.repository.ProductRepository;
//import online.fivediamond.be.util.AccountUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CartService {
//    @Autowired
//    CartRepository cartRepository;
//
//    @Autowired
//    AuthenticationRepository authenticationRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    AccountUtil accountUtil;
//
//    public String addToCart(long productID) {
//        Account account = accountUtil.accountCurrent();
//        Cart cart = account.getCart();
//        Product product = productRepository.findById(productID).orElseThrow();
////    cart.setProducts(cart.getProducts().add(product));
//
////        if(cart.getProducts().contains(product)) {
////            return "Existed";
////        }
////
////        cart.getProducts().add(product);999
//        return "Successfully";
//    }
//}
