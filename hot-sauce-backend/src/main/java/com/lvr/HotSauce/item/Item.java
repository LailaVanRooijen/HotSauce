package com.lvr.HotSauce.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lvr.HotSauce.cartitem.CartItem;
import com.lvr.HotSauce.ingredient.Ingredient;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Item {
  @OneToMany(mappedBy = "item")
  @JsonIgnore
  private final Set<CartItem> cartItems = new HashSet<>();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter private String name;
  @Setter private String description;
  @Setter private Double price;
  @Setter private HeatLevel heatLevel;

  @ManyToMany private List<Ingredient> ingredients = new ArrayList<>();

  public Item(
      String name,
      String description,
      Double price,
      HeatLevel heatLevel,
      List<Ingredient> ingredients) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.heatLevel = heatLevel;
    this.ingredients = ingredients;
  }

  public void addIngredients(List<Ingredient> newIngredients) {
    ingredients.addAll(newIngredients);
  }
}
