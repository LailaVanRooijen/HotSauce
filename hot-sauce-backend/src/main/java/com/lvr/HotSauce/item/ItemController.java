package com.lvr.HotSauce.item;

import java.net.URI;
import java.util.List;

import com.lvr.HotSauce.ingredient.IngredientService;
import com.lvr.HotSauce.routes.ControllerRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.ITEM_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class ItemController {
  private final ItemService itemService;

  public ItemController(ItemService itemService, IngredientService ingredientService) {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getAll(
      @RequestParam(required = false) List<String> ingredients,
      @RequestParam(required = false, name = "heatlevel") List<String> heatLevels,
      @RequestParam(required = false) Double min,
      @RequestParam(required = false) Double max) {
    // case sensitive!! ook in de url als query param
    return ResponseEntity.ok(itemService.getAll(ingredients, heatLevels, min, max));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getById(@PathVariable Long id) {
    return ResponseEntity.ok(itemService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Item> item(@RequestBody Item item) {
    Item savedItem = itemService.save(item);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedItem.getId())
            .toUri();

    return ResponseEntity.created(location).body(savedItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Item> delete(@PathVariable Long id) {
    itemService.delete(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Item> patch(@PathVariable Long id, @RequestBody Item item) {
    itemService.patch(id, item);
    return ResponseEntity.noContent().build();
  }
}
