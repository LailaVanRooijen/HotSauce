package com.lvr.HotSauce.purchase;

import com.lvr.HotSauce.purchaseitem.PurchaseItem;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Purchase {
  private final LocalDateTime createdOn = LocalDateTime.now();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Setter @OneToMany private Set<PurchaseItem> purchaseItems;
  @Setter private Boolean isOpen;
}
