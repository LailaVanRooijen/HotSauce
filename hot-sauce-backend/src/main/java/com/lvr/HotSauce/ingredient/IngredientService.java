package com.lvr.HotSauce.ingredient;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
  public IngredientService(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  private final IngredientRepository ingredientRepository;

  public List<Ingredient> getAll() {
    return ingredientRepository.findAll();
  }

  public Ingredient getByName(String name) {
    return ingredientRepository.findByNameIgnoreCase(name);
  }

  public Ingredient getById(Long id) {
    return ingredientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Ingredient save(String ingredient) {
    Ingredient savedIngredient = new Ingredient(ingredient);
    return ingredientRepository.save(savedIngredient);
  }
}
