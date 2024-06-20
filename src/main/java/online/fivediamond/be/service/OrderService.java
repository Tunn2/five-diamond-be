package online.fivediamond.be.service;

import online.fivediamond.be.entity.*;
import online.fivediamond.be.model.order.OrderCreationRequest;
import online.fivediamond.be.repository.CartItemRepository;
import online.fivediamond.be.repository.CartRepository;
import online.fivediamond.be.repository.OrderItemRepository;
import online.fivediamond.be.repository.OrderRepository;
import online.fivediamond.be.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AccountUtil accountUtil;

    @Autowired
    CartItemRepository cartItemRepository;

    public Order convertCartToOrder(long cartId, OrderCreationRequest request) {
        Account account = accountUtil.accountCurrent();
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
        Order order = new Order();
        order.setAccount(account);
        order.setOrderDate(new Date());

        return order;
    }
}
