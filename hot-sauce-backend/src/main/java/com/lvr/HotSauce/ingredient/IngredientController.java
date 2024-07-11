package com.lvr.HotSauce.ingredient;

import java.net.URI;
import java.util.List;

import com.lvr.HotSauce.routes.ControllerRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = {"${client}"})
@RequestMapping(ControllerRoutes.INGREDIENT_ROUTE)
@RestController
public class IngredientController {
  private final IngredientService ingredientService;

  public IngredientController(IngredientService ingredientService) {
    this.ingredientService = ingredientService;
  }

  @GetMapping
  public ResponseEntity<List<Ingredient>> getAll() {
    return ResponseEntity.ok(ingredientService.getAll());
  }

  @GetMapping("/get")
  public ResponseEntity<Ingredient> getByName(@RequestParam String name) {
    System.out.println(ingredientService.getByName(name));
    return ResponseEntity.ok(ingredientService.getByName(name));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ingredient> getById(@PathVariable Long id) {
    return ResponseEntity.ok(ingredientService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Ingredient> save(@RequestBody String ingredient) {
    Ingredient savedIngredient = ingredientService.save(ingredient);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedIngredient.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedIngredient);
  }
}
