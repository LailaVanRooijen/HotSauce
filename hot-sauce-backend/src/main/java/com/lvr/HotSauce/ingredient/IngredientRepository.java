package com.lvr.HotSauce.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
  Ingredient findByNameIgnoreCase(String name);
}
