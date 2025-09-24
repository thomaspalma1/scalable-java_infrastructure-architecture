package br.com.alura.codechella.controller;

import br.com.alura.codechella.domain.authentication.entity.User;
import br.com.alura.codechella.domain.event.service.PurchaseService;
import br.com.alura.codechella.domain.event.vo.PurchaseData;
import br.com.alura.codechella.domain.event.vo.PurchaseRequestData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("purchases")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseData>> listMyPurchases(@AuthenticationPrincipal User loggedInUser) {
        var purchases = service.listMyPurchases(loggedInUser);
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("{id}")
    public ResponseEntity<PurchaseData> getDetails(@PathVariable Long id) {
        var purchase = service.getPurchaseDetails(id);
        return ResponseEntity.ok(purchase);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PurchaseData> makePurchase(@RequestBody @Valid PurchaseRequestData makePurchaseData, @AuthenticationPrincipal User loggedInUser, UriComponentsBuilder uriBuilder) {
        var purchaseData = service.makePurchase(makePurchaseData, loggedInUser);
        var uri = uriBuilder.path("purchases/{id}").buildAndExpand(purchaseData.id()).toUri();
        return ResponseEntity.created(uri).body(purchaseData);
    }

}
