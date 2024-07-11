package com.lvr.HotSauce.cartitem;

import java.net.URI;
import java.util.List;

import com.lvr.HotSauce.routes.ControllerRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.CART_ITEM_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class CartItemController {
  private final CartItemService cartItemService;

  public CartItemController(CartItemService cartItemService) {
    this.cartItemService = cartItemService;
  }

  @GetMapping
  public ResponseEntity<List<CartItem>> getAll() {
    return ResponseEntity.ok(cartItemService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CartItem> getById(@PathVariable Long id) {
    return ResponseEntity.ok(cartItemService.getById(id));
  }

  @PostMapping
  public ResponseEntity<CartItem> save(@RequestBody CartItemDTO cartItem) {
    CartItem savedCartItem = cartItemService.save(cartItem.quantity(), cartItem.id());
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedCartItem.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedCartItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CartItem> delete(@PathVariable Long id) {
    cartItemService.delete(id);
    return ResponseEntity.ok().build();
  }

  // TODO Patch
  @PatchMapping("/{id}")
  public ResponseEntity<CartItem> update(@PathVariable Long id, @RequestBody CartItem item) {
    cartItemService.update(id, item);
    return ResponseEntity.noContent().build();
  }
}
