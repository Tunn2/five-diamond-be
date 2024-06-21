package online.fivediamond.be.service;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.entity.Account;
import online.fivediamond.be.entity.Cart;
import online.fivediamond.be.entity.CartItem;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.repository.AuthenticationRepository;
import online.fivediamond.be.repository.CartItemRepository;
import online.fivediamond.be.repository.CartRepository;
import online.fivediamond.be.repository.ProductLineRepository;
import online.fivediamond.be.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    AccountUtil accountUtil;

    @Autowired
    CartItemRepository cartItemRepository;
    public String addToCart(long id) {
        Account account = accountUtil.accountCurrent();
        Cart cart = account.getCart();
        ProductLine productLine = productLineRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        Set<CartItem> cartItems = cart.getCartItems();

        if(cartItems.isEmpty()) {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProductLine(productLine);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            return "Add to cart successfully";
        } else {
            for (CartItem item : cartItems) {
                if (item.getProductLine().getId() == id) {
                    item.setQuantity(item.getQuantity() + 1);
                    cartItemRepository.save(item);
                    return "Update quantity this product in cart";
                }
            }
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProductLine(productLine);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            return "Add to cart successfully";
        }
    }

    public void delete(long id) {
        cartItemRepository.deleteById(id);
    }
    public Cart getCart() {
        Account account = accountUtil.accountCurrent();
        return account.getCart();
    }

//    boolean checkQuantity() {
//        Account account = accountUtil.accountCurrent();
//        Cart cart = account.getCart();
//        int quantityInStock =
//    }
}
