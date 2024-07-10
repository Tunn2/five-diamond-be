package online.fivediamond.be.service;


import online.fivediamond.be.entity.Order;
import online.fivediamond.be.entity.OrderItem;
import online.fivediamond.be.entity.Product;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.enums.Role;
import online.fivediamond.be.model.dto.AccountTotalResponse;
import online.fivediamond.be.model.dto.BestSeller;
import online.fivediamond.be.model.dto.MostSellerResponse;
import online.fivediamond.be.model.dto.RevenueTotalResponse;
import online.fivediamond.be.repository.AuthenticationRepository;
import online.fivediamond.be.repository.OrderRepository;
import online.fivediamond.be.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    public AccountTotalResponse countUser() {
        int customerCount = authenticationRepository.countByRole(Role.CUSTOMER.toString());
        int managerCount = authenticationRepository.countByRole(Role.MANAGER.toString());
        int adminCount = authenticationRepository.countByRole(Role.ADMIN.toString());
        int deliverCount = authenticationRepository.countByRole(Role.DELIVERY.toString());
        int staffCount = authenticationRepository.countByRole(Role.SALES.toString());
        int total = customerCount + adminCount + deliverCount + staffCount + managerCount;

        AccountTotalResponse accountTotalResponse = new AccountTotalResponse();
        accountTotalResponse.setMemberTotal(total);
        accountTotalResponse.setAdminQuantity(adminCount);
        accountTotalResponse.setDeliverQuantity(deliverCount);
        accountTotalResponse.setCustomerQuantity(customerCount);
        accountTotalResponse.setManagerQuantity(managerCount);
        accountTotalResponse.setSalesQuantity(staffCount);
        return accountTotalResponse;
    }

    public List<AccountTotalResponse> getAccountByMonth(int year) {
        List<AccountTotalResponse> list = new ArrayList<>();
        for(int i = 1; i <= 12; i++) {
            int customerCount = authenticationRepository.countByRoleByMonth(Role.CUSTOMER.toString(), i, year);
            int managerCount = authenticationRepository.countByRoleByMonth(Role.MANAGER.toString(), i, year);
            int adminCount = authenticationRepository.countByRoleByMonth(Role.ADMIN.toString(), i, year);
            int deliverCount = authenticationRepository.countByRoleByMonth(Role.DELIVERY.toString(), i, year);
            int staffCount = authenticationRepository.countByRoleByMonth(Role.SALES.toString(), i, year);
            int total = customerCount + adminCount + deliverCount + staffCount + managerCount;

            AccountTotalResponse accountTotalResponse = new AccountTotalResponse();
            accountTotalResponse.setMemberTotal(total);
            accountTotalResponse.setAdminQuantity(adminCount);
            accountTotalResponse.setDeliverQuantity(deliverCount);
            accountTotalResponse.setCustomerQuantity(customerCount);
            accountTotalResponse.setManagerQuantity(managerCount);
            accountTotalResponse.setSalesQuantity(staffCount);
            accountTotalResponse.setMonth(i);
            list.add(accountTotalResponse);
        }
        return list;
    }

    public List<RevenueTotalResponse> getRevenueByMonth(int year) {
        List<RevenueTotalResponse> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            List<Order> orderList = orderRepository.findOrderByOrderDate(i, year);
            double totalRevenueByMonth = 0;
            double totalCost = 0;
            double totalProfitByMonth = 0;
            if(!orderList.isEmpty()) {
                for (Order order: orderList) {
                    Set<OrderItem> orderItems = order.getOrderItems();
                    for(OrderItem orderItem : orderItems) {
                        Product product = orderItem.getProduct();
                        ProductLine productLine = product.getProductLine();
                        totalCost += productLine.getPrice();
                    }
                    totalRevenueByMonth += order.getTotalAmount();
                }
            }
            totalProfitByMonth = totalRevenueByMonth - totalCost;

            list.add(new RevenueTotalResponse(i, totalRevenueByMonth, totalProfitByMonth, orderList));
        }

        return list;
    }

//    @Transactional(readOnly = true)
    public List<MostSellerResponse> getMostSellerByMonth(int year) {
        List<MostSellerResponse> list = new ArrayList<>();
        for(int i = 1; i <= 12; i++) {
            List<Object[]> productLines = productLineRepository.getBestSellersByMonthAndYear(i, year);
            List<BestSeller> bestSellers = productLines.stream()
                    .map(result -> new BestSeller((Long) result[0], ((Number) result[1]).longValue()))
                    .collect(Collectors.toList());
            System.out.println(productLines);
            list.add(new MostSellerResponse(i, bestSellers));   
        }
        return list;
    }

}
