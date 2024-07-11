package com.lvr.HotSauce.purchaseitem;

import com.lvr.HotSauce.routes.ControllerRoutes;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.PURCHASE_ITEM_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class PurchaseItemController {
  private final PurchaseItemService purchaseItemService;

  public PurchaseItemController(PurchaseItemService purchaseItemService) {
    this.purchaseItemService = purchaseItemService;
  }

  @GetMapping
  public ResponseEntity<List<PurchaseItem>> getAll() {
    return ResponseEntity.ok(purchaseItemService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PurchaseItem> getById(@PathVariable Long id) {
    return ResponseEntity.ok(purchaseItemService.getById(id));
  }

  @PostMapping
  public ResponseEntity<PurchaseItem> save(@RequestBody PurchaseItemDTO purchaseItemDTO) {
    PurchaseItem savedPurchaseItem =
        purchaseItemService.save(purchaseItemDTO.quantity(), purchaseItemDTO.id());
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPurchaseItem.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedPurchaseItem);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PurchaseItem> update(@RequestBody PurchaseItemDTO purchaseItemDTO) {
    PurchaseItem updatedPurchaseItem =
        purchaseItemService.update(purchaseItemDTO.quantity(), purchaseItemDTO.id());
    return ResponseEntity.ok(updatedPurchaseItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PurchaseItem> delete(@PathVariable Long id) {
    System.out.println("joj");
    purchaseItemService.delete(id);
    return ResponseEntity.ok().build();
  }
}
