package online.fivediamond.be.service;

import online.fivediamond.be.entity.*;
import online.fivediamond.be.enums.CancelOrderStatus;
import online.fivediamond.be.enums.OrderStatus;
import online.fivediamond.be.enums.Role;
import online.fivediamond.be.model.order.*;
import online.fivediamond.be.repository.*;
import online.fivediamond.be.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    WarrantyRepository warrantyRepository;

    @Autowired
    CancelOrderRepository cancelOrderRepository;

    @Transactional
    public Order convertCartToOrder(OrderCreationRequest request) {
        Account account = accountUtil.accountCurrent();
        Cart cart = cartRepository.findById(account.getCart().getId()).orElseThrow();
        Set<CartItem> cartItems = cart.getCartItems();
//        double price = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        Order order = new Order();
        order = orderRepository.save(order);
        Promotion promotion = promotionRepository.findByCode(request.getPromotionCode());

        for (CartItem item : cartItems) {
            ProductLine productLine = item.getProductLine();
            List<Product> products = productRepository.findAvailableProducts(item.getQuantity(), productLine.getId());

            //check available while making payment
//            if (products.size() < item.getQuantity()) {
//                orderRepository.deleteById(order.getId());
//                throw new RuntimeException("Quantity of " + item.getProductLine().getName() + " in stock is not enough");
//            }
            int count = 0;
            for (Product product : products) {
                count++;
                OrderItem orderItem = new OrderItem();
                Warranty warranty = new Warranty();
                warranty.setAccount(account);
                warranty.setOrderDate(LocalDate.now());
                warranty.setExpiredDate(LocalDate.now().plusYears(2));
                warranty.setProduct(product);
                orderItem.setPrice(productLine.getPrice());
                orderItem.setProduct(product);
                orderItem.setOrder(order);
                product.setSale(true);
                warrantyRepository.save(warranty);
                productRepository.save(product);
                orderItemRepository.save(orderItem);
                orderItems.add(orderItem);
            }
            productLine.setQuantity(productLine.getQuantity() - count);
            productLineRepository.save(productLine);
            cartItemRepository.deleteCartItem(item.getId());
        }
        order.setCannotContactTimes(0);
        order.setPromotion(promotion);
        Set<OrderItem> orderItemSet = new HashSet<>(orderItems);
        order.setOrderItems(orderItemSet);
        order.setTotalAmount(request.getTotalAmount());
        order.setCustomer(account);
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setOrderDate(LocalDateTime.now());
        order.setFullname(request.getFullname());
        order.setOrderStatus(request.getOrderStatus());
        order.setNote(request.getNote());
        order.setCannotContactTimes(0);
        account.setRewardPoint(account.getRewardPoint() + request.getTotalAmount());
        authenticationRepository.save(account);
        order = orderRepository.save(order); // Save the order again to ensure it contains all order items
        return order;
    }


    public List<Order> getOrdersByAccountId() {
        Account account = accountUtil.accountCurrent();
        List<Order> orders = orderRepository.findByCustomerId(account.getId());
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

    public Order updateOrderStatus(long id, OrderStatusUpdateRequest request, long accountID) {
        Order order = orderRepository.findById(id).orElseThrow();
        Account account = authenticationRepository.findById(accountID).orElseThrow(() -> new RuntimeException("Not found"));
        if(account.getRole() == Role.SALES)
            order.setStaff(account);
        if(account.getRole() == Role.DELIVERY)
            order.setShipper(account);
        order.setOrderStatus(request.getOrderStatus());
        return orderRepository.save(order);
    }

    public Order cancelOrder(long id, OrderCancelRequest request) {
        Order order = orderRepository.findById(id).orElseThrow();
        CanceledOrder canceledOrder = new CanceledOrder();
        order.setOrderStatus(OrderStatus.CANCELED);

        canceledOrder.setCancelOrderStatus(CancelOrderStatus.PENDING);
        canceledOrder.setRefundAmount(order.getTotalAmount());
        canceledOrder.setAccount(order.getCustomer());
        canceledOrder.setReason(request.getCanceledNote());
        canceledOrder.setCancelDate(LocalDate.now());
        canceledOrder.setOrder(order);

        cancelOrderRepository.save(canceledOrder);
        return orderRepository.save(order);
    }

    public Order deliveredOrder(long id, OrderDeliveryRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        order.setShippingDate(new Date());
        order.setImgConfirmUrl(request.getImgConfirmUrl());
        return orderRepository.save(order);
    }

    public List<CanceledOrder> getListCanceledOrder() {
        return cancelOrderRepository.findAll();
    }

    public Order cannotContact(long id, OrderCancelRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if(order.getCannotContactTimes() == 3) {
            cancelOrder(id, request);
        }else {
            order.setCannotContactTimes(order.getCannotContactTimes() + 1);
        }
        return orderRepository.save(order);
    }

    public CanceledOrder refund(long id) {
        CanceledOrder canceledOrder = cancelOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        canceledOrder.setCancelOrderStatus(CancelOrderStatus.FINISHED);
        return cancelOrderRepository.save(canceledOrder);
    }

}
