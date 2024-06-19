package online.fivediamond.be.api;

import online.fivediamond.be.model.RechargeRequestDTO;
import online.fivediamond.be.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/wallet")
@RestController
public class WalletAPI {
    @Autowired
    WalletService walletService;

    @PostMapping("/vnpay")
    public ResponseEntity createUrl(@RequestBody RechargeRequestDTO requestDTO) throws Exception {
        return ResponseEntity.ok(walletService.createUrl(requestDTO));
    }

}
