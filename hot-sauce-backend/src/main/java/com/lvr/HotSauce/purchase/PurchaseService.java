package com.lvr.HotSauce.purchase;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
  private final PurchaseRepository purchaseRepository;

  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public List<Purchase> getAll() {
    return purchaseRepository.findAll();
  }

  public Purchase getById(Long id) {
    Optional<Purchase> purchase = purchaseRepository.findById(id);
    if (purchase.isEmpty())
      throw new EntityNotFoundException("Purchase of id " + id + " can not be found");
    return purchase.get();
  }

  public Purchase save(Purchase purchase) {
    return purchaseRepository.save(purchase);
  }

  public Purchase openOrClosePurchase(Long id, Boolean isOpen) {
    Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
    if (optionalPurchase.isEmpty())
      throw new EntityNotFoundException("Purchase of id " + id + " can not be found");
    Purchase purchase = optionalPurchase.get();
    purchase.setIsOpen(isOpen);
    return purchaseRepository.save(purchase);
  }

  public void delete(Long id) {
    Optional<Purchase> purchase = purchaseRepository.findById(id);
    if (purchase.isEmpty())
      throw new EntityNotFoundException("Purchase of id " + id + " can not be found");
    purchaseRepository.deleteById(id);
  }
}
