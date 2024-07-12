package online.fivediamond.be.api;

import online.fivediamond.be.model.dto.RechargeRequestDTO;
import online.fivediamond.be.service.CartService;
import online.fivediamond.be.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/wallet")
@RestController
public class WalletAPI {
    @Autowired
    WalletService walletService;

    @Autowired
    CartService cartService;

    @PostMapping("/vnpay")
    public ResponseEntity createUrl(@RequestBody RechargeRequestDTO requestDTO) throws Exception {
        String checkQuantity = cartService.checkQuantity();
        if(!checkQuantity.equals("OK")) {
            return ResponseEntity.ok(checkQuantity);
        }
        return ResponseEntity.ok(walletService.createUrl(requestDTO));
    }


}
