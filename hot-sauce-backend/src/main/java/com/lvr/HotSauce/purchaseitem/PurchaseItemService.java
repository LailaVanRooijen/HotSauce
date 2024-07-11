package com.lvr.HotSauce.purchaseitem;

import com.lvr.HotSauce.item.Item;
import com.lvr.HotSauce.item.ItemService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PurchaseItemService {
  private final PurchaseItemRepository purchaseItemRepository;
  private final ItemService itemService;

  public PurchaseItemService(
      PurchaseItemRepository purchaseItemRepository, ItemService itemService) {
    this.purchaseItemRepository = purchaseItemRepository;
    this.itemService = itemService;
  }

  public List<PurchaseItem> getAll() {
    return purchaseItemRepository.findAll();
  }

  public PurchaseItem getById(Long id) {
    Optional<PurchaseItem> purchaseItem = purchaseItemRepository.findById(id);
    if (purchaseItem.isEmpty())
      throw new EntityNotFoundException("PurchaseItem of id " + id + " can not be found");
    return purchaseItem.get();
  }

  public PurchaseItem save(Integer quantity, Long id) {
    Item item = itemService.getById(id);
    return purchaseItemRepository.save(PurchaseItem.of(item, quantity));
  }

  public PurchaseItem update(Integer quantity, Long purchaseItemId) {
    Optional<PurchaseItem> optionalPurchaseItem = purchaseItemRepository.findById(purchaseItemId);
    if (optionalPurchaseItem.isEmpty())
      throw new EntityNotFoundException("Purchase of id " + purchaseItemId + " can not be found");
    PurchaseItem purchaseItem = optionalPurchaseItem.get();
    purchaseItem.setQuantity(quantity);
    return purchaseItemRepository.save(purchaseItem);
  }

  public void delete(Long id) {
    Optional<PurchaseItem> purchaseItem = purchaseItemRepository.findById(id);
    if (purchaseItem.isEmpty())
      throw new EntityNotFoundException("Purchase of id " + id + " can not be found");
    purchaseItemRepository.deleteById(id);
  }
}
