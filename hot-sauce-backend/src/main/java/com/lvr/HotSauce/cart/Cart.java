package com.lvr.HotSauce.cart;


import com.lvr.HotSauce.cartitem.CartItem;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.LAZY)
  @Setter
  private Set<CartItem> cartItems = new HashSet<>();

  public void addCartItem(CartItem cartItem) {
    cartItems.add(cartItem);
  }
}
