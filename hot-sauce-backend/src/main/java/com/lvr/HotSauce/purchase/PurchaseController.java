package com.lvr.HotSauce.purchase;

import java.net.URI;
import java.util.List;

import com.lvr.HotSauce.routes.ControllerRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.PURCHASE_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class PurchaseController {
  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @GetMapping
  public ResponseEntity<List<Purchase>> getAll() {
    List<Purchase> purchases = purchaseService.getAll();
    return ResponseEntity.ok(purchases);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Purchase> getById(@PathVariable Long id) {
    Purchase purchase = purchaseService.getById(id);
    return ResponseEntity.ok(purchase);
  }

  @PostMapping
  public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
    Purchase savedPurchase = purchaseService.save(purchase);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(purchase.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedPurchase);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Purchase> update(@PathVariable Long id, Boolean isOpen) {
    Purchase updatedPurchase = purchaseService.openOrClosePurchase(id, isOpen);
    return ResponseEntity.ok().body(updatedPurchase);
  }

  // geen delete.
}
