package com.lvr.HotSauce.cartitem;


import com.lvr.HotSauce.customExceptions.InvalidQuantityException;
import com.lvr.HotSauce.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class CartItem {
  public static CartItem of(Integer quantity, Item item) {
    if (quantity < 0 || quantity > 99)
      throw new InvalidQuantityException("Quantity must be a minimum of 0 and a maximum of 99");
    return new CartItem(item, quantity);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter private Integer quantity;

  @ManyToOne @Setter
  Item item;

  public CartItem(Item item, Integer quantity) {
    this.quantity = quantity;
    this.item = item;
  }
}
