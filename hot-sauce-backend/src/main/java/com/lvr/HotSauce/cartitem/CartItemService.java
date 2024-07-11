package com.lvr.HotSauce.cartitem;

import com.lvr.HotSauce.item.Item;
import com.lvr.HotSauce.item.ItemService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemService {
  private final CartItemRepository cartItemRepository;
  private final ItemService itemService;

  public List<CartItem> getAll() {
    return cartItemRepository.findAll();
  }

  public CartItem getById(Long id) {

    return cartItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public CartItem save(Integer quantity, Long id) {
    Item item = itemService.getById(id);
    return cartItemRepository.save(CartItem.of(quantity, item));
  }

  public CartItem update(Long id, CartItem cartItemUpdate) {
    CartItem cartItem = cartItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    if (cartItemUpdate.getItem() != null) {
      throw new IllegalArgumentException(
          "CartItemService: update: you can not bind another Item to CartItem");
    }
    if (cartItemUpdate.getQuantity() != null) {
      cartItem.setQuantity(cartItemUpdate.getQuantity());
    }
    return cartItemRepository.save(cartItem);
  }

  public void delete(Long id) {
    Optional<CartItem> cartItem = cartItemRepository.findById(id);
    if (cartItem.isEmpty()) throw new EntityNotFoundException();
    cartItemRepository.deleteById(id);
  }
}
