package online.fivediamond.be.service;

import online.fivediamond.be.entity.*;
import online.fivediamond.be.model.order.OrderCreationRequest;
import online.fivediamond.be.repository.*;
import online.fivediamond.be.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {
@Autowired
    ProductLineRepository productLineRepository;

@Autowired
ProductRepository productRepository;

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


    @Transactional
    public Order convertCartToOrder(OrderCreationRequest request) {
        Account account = accountUtil.accountCurrent();
        Cart cart = cartRepository.findById(account.getCart().getId()).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
        Order order = new Order();
        order = orderRepository.save(order);
        // productLines;
        for(CartItem item: cartItems) {
            ProductLine productLine = item.getProductLine();
            List<Product> products = productRepository.findAvailableProducts(item.getQuantity(), productLine.getId());
            for(Product product: products){
                OrderItem orderItem = new OrderItem();
                orderItem.setPrice(productLine.getPrice());
                orderItem.setProduct(product);
                orderItem.setOrder(order);
                product.setSale(true);
                productRepository.save(product);
                orderItemRepository.save(orderItem);
            }
            System.out.println(item.getId());

            //dit me dong 61 deo co transactional voi modiying la no loi cay vc
            cartItemRepository.deleteCartItem(item.getId());
        }

        order.setAccount(account);
        order.setPhone(request.getPhone());
        orderRepository.save(order);
        return order;
    }
}
