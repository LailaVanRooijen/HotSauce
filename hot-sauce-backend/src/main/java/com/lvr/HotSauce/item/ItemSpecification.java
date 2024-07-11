package com.lvr.HotSauce.item;

import com.lvr.HotSauce.ingredient.Ingredient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {
  public static Specification<Item> hasIngredients(List<Ingredient> ingredients) {
    return (root, query, criteriaBuilder) -> {
      // omdat het een List betreft in de entiteit moet je een join toevoegen
      Join<Item, Ingredient> ingredientJoin = root.join("ingredients");
      CriteriaBuilder.In<Ingredient> inClause =
          criteriaBuilder.in(
              ingredientJoin); // inClause was om met de for loop door de lijst te kunnen loopen
      for (Ingredient ingredient : ingredients) {
        inClause.value(ingredient);
      }
      return inClause;
    };
  }

  public static Specification<Item> hasHeatLevel(List<HeatLevel> heatLevels) {
    return (root, query, criteriaBuilder) -> {
      CriteriaBuilder.In<HeatLevel> inClause = criteriaBuilder.in(root.get("heatLevel"));
      for (HeatLevel heatLevel : heatLevels) {
        inClause.value(heatLevel);
      }
      return inClause;
    };
  }

  // price
  public static Specification<Item> hasPriceRangeMinMax(Double min, Double max) {
    return (((root, query, criteriaBuilder) -> {
      // voorbeeld voor comments, min is 10.0 max is 20.0
      if (min != null && max != null) {
        return criteriaBuilder.between(
            root.get("price"), min, max); // SELECT * FROM Item WHERE price BETWEEN 10.0 AND 20.0;
      } else if (min != null) {
        return criteriaBuilder.greaterThanOrEqualTo(
            root.get("price"), min); // SELECT * FROM Item WHERE price >= 10.0;
      } else if (max != null) {
        return criteriaBuilder.lessThanOrEqualTo(
            root.get("price"), max); // SELECT * FROM Item WHERE price <= 20.0;
      } else {
        return criteriaBuilder.conjunction(); // SELECT * from
        // Item;
      }
    }));
  }
}
