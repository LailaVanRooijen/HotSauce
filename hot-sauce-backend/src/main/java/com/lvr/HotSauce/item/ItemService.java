package com.lvr.HotSauce.item;

import com.lvr.HotSauce.ingredient.Ingredient;
import com.lvr.HotSauce.ingredient.IngredientService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;
  private final IngredientService ingredientService;

  public List<Item> getAll(
      List<String> ingredients, List<String> heatLevels, Double min, Double max) {
    Specification<Item> specification = Specification.where(null);

    if (ingredients != null && !ingredients.isEmpty()) {
      List<Ingredient> ingredientList =
          ingredients.stream().map(ingredientService::getByName).toList();
      specification = specification.and(ItemSpecification.hasIngredients(ingredientList));
    }

    if (heatLevels != null) {
      List<HeatLevel> heatLevelList =
          heatLevels.stream().map(heatLevel -> HeatLevel.valueOf(heatLevel.toUpperCase())).toList();
      specification = specification.and(ItemSpecification.hasHeatLevel(heatLevelList));
    }
    if (min != null || max != null) {
      specification = specification.and(ItemSpecification.hasPriceRangeMinMax(min, max));
    }

    return itemRepository.findAll(specification);
  }

  public Item getById(Long id) {
    return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  // create, update and delete
  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public Item update(Item item) {
    return itemRepository.save(item);
  }

  public void delete(Long id) {
    Optional<Item> item = itemRepository.findById(id);
    if (item.isEmpty()) throw new EntityNotFoundException();
    itemRepository.deleteById(id);
  }

  public void patch(Long id, Item patchItem) {
    Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    if (patchItem.getIngredients() != null) {
      item.addIngredients(patchItem.getIngredients());
    }
    if (patchItem.getName() != null) {
      item.setName(patchItem.getName());
    }
    if (patchItem.getDescription() != null) {
      item.setDescription(patchItem.getDescription());
    }
    if (patchItem.getPrice() != null) {
      item.setPrice(patchItem.getPrice());
    }
    if (patchItem.getHeatLevel() != null) {
      System.out.println(patchItem.getHeatLevel());
      item.setHeatLevel(patchItem.getHeatLevel());
    }
    itemRepository.save(item);
    // TODO in welke gevallen moet ik een exception throwen?
  }
}
