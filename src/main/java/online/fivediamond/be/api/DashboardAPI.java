package online.fivediamond.be.api;

import online.fivediamond.be.repository.OrderRepository;
import online.fivediamond.be.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;

@RestController
@RequestMapping("api/dashboard")
@CrossOrigin("*")
public class DashboardAPI {

    @Autowired
    DashboardService dashboardService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("account")
    public ResponseEntity getAccount() {
        return ResponseEntity.ok(dashboardService.countUser());
    }

    @GetMapping("revenue")
    public ResponseEntity getTotalRevenue() {
        return ResponseEntity.ok(dashboardService.getRevenueByMonth(Year.now().getValue()));
    }

    @GetMapping("account-by-month")
    public ResponseEntity getAccountByMonth() {
        return ResponseEntity.ok(dashboardService.getAccountByMonth(Year.now().getValue()));
    }

    @GetMapping("best-seller")
    public ResponseEntity getBestSellerByMonth() {
        return ResponseEntity.ok(dashboardService.getMostSellerByMonth(Year.now().getValue()));
    }
}