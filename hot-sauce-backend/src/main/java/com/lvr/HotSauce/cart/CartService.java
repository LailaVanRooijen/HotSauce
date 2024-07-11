package com.lvr.HotSauce.cart;


import com.lvr.HotSauce.cartitem.CartItem;
import com.lvr.HotSauce.cartitem.CartItemDTO;
import com.lvr.HotSauce.cartitem.CartItemRepository;
import com.lvr.HotSauce.item.Item;
import com.lvr.HotSauce.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final ItemRepository itemRepository;
  private final CartItemRepository cartItemRepository;

  public List<Cart> getAll() {
    return cartRepository.findAll();
  }

  public Cart getById(Long id) {
    return cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }

  public Cart addItemToCart(Long cartId, CartItemDTO cartItemDTO) {
    Cart cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
    Item item = itemRepository.findById(cartItemDTO.id()).orElseThrow(EntityNotFoundException::new);
    CartItem cartItem = new CartItem(item, cartItemDTO.quantity());
    cartItemRepository.save(cartItem);
    cart.addCartItem(cartItem);
    return cartRepository.save(cart);
  }

  public void delete(Long id) {
    Optional<Cart> cart = cartRepository.findById(id);
    if (cart.isEmpty()) throw new EntityNotFoundException();
    cartRepository.deleteById(id);
  }

  // TODO Patch
}
