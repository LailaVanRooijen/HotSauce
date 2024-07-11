package com.lvr.HotSauce.cart;


import java.net.URI;
import java.util.List;

import com.lvr.HotSauce.cartitem.CartItemDTO;
import com.lvr.HotSauce.routes.ControllerRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.CART_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<List<Cart>> getAll() {
    return ResponseEntity.ok(cartService.getAll());
  }

  @GetMapping("/{id}")
  public Cart getById(@PathVariable Long id) {
    return cartService.getById(id);
  }

  @PostMapping
  public ResponseEntity<Cart> save(@RequestBody Cart cart) {
    Cart savedCart = cartService.save(cart);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedCart.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedCart);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Cart> delete(@PathVariable Long id) {
    cartService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{cartId}/add")
  public ResponseEntity<Cart> addItemToCart(
      @PathVariable Long cartId, @RequestBody CartItemDTO item) {
    return ResponseEntity.ok(cartService.addItemToCart(cartId, item));
  }

  // TODO ik moet een item kunnnen verwijderen, maar ook de quantity kunnen aanpassen. Wat zijn
  // goede endpoints hiervoor?
}
