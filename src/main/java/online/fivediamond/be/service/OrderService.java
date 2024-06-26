package online.fivediamond.be.service;

import online.fivediamond.be.entity.*;
import online.fivediamond.be.enums.OrderStatus;
import online.fivediamond.be.model.order.OrderCreationRequest;
import online.fivediamond.be.model.order.OrderResponse;
import online.fivediamond.be.model.order.OrderStatusUpdateRequest;
import online.fivediamond.be.repository.*;
import online.fivediamond.be.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    AuthenticationRepository authenticationRepository;


    @Transactional
    public Order convertCartToOrder(OrderCreationRequest request) {
        Account account = accountUtil.accountCurrent();
        Cart cart = cartRepository.findById(account.getCart().getId()).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
        double price = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        Order order = new Order();
        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            ProductLine productLine = item.getProductLine();
            List<Product> products = productRepository.findAvailableProducts(item.getQuantity(), productLine.getId());
            if (products.size() < item.getQuantity()) {
                orderRepository.deleteById(order.getId());
                throw new RuntimeException("Quantity of " + item.getProductLine().getName() + " in stock is not enough");
            }
            int count = 0;
            for (Product product : products) {
                count++;
                OrderItem orderItem = new OrderItem();
                orderItem.setPrice(productLine.getPrice());
                orderItem.setProduct(product);
                price += productLine.getPrice();
                orderItem.setOrder(order);
                product.setSale(true);
                productRepository.save(product);
                orderItemRepository.save(orderItem);
                orderItems.add(orderItem);
            }
            productLine.setQuantity(productLine.getQuantity() - count);
            productLineRepository.save(productLine);
            cartItemRepository.deleteCartItem(item.getId());
        }
        Set<OrderItem> orderItemSet = new HashSet<>(orderItems);
        order.setOrderItems(orderItemSet);
        order.setTotalAmount(price);
        order.setAccount(account);
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setOrderDate(LocalDateTime.now());
        order.setFullname(request.getFullname());
        order.setOrderStatus(request.getOrderStatus());
        order.setNote(request.getNote());
        account.setRewardPoint(account.getRewardPoint() + price);
        authenticationRepository.save(account);
        order = orderRepository.save(order); // Save the order again to ensure it contains all order items
        return order;
    }


    public List<Order> getOrdersByAccountId() {
        Account account = accountUtil.accountCurrent();
        List<Order> orders = orderRepository.findByAccountId(account.getId());
        return orders;
    }

    public List<Order> getPendingOrders() {
        return orderRepository.findByOrderStatus(OrderStatus.PENDING);
    }

    public List<Order> getConfirmedOrders() {
        return orderRepository.findByOrderStatus(OrderStatus.CONFIRMED);
    }

    public List<Order> getProcessingOrders() {
        return orderRepository.findByOrderStatus(OrderStatus.PROCESSING);
    }

    public List<Order> getShippedOrders() {
        return orderRepository.findByOrderStatus(OrderStatus.SHIPPED);
    }

    public List<Order> getDeliveredOrders() {
        return orderRepository.findByOrderStatus(OrderStatus.DELIVERED);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
        Set<OrderItem> orderItemSet = new HashSet<>(orderItems);
        for(OrderItem item: orderItemSet) {
            System.out.println(item.getId());
        }
        order.setOrderItems(orderItemSet);
        return order;
    }

    public Order updateOrderStatus(long id, OrderStatusUpdateRequest request) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrderStatus(request.getOrderStatus());
        return orderRepository.save(order);
    }

}
