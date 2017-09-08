package fr.coffeemachine.infra.repositories;

import fr.coffeemachine.infra.entities.SaleEntity;

public interface DBSaleRepository {
  SaleEntity save(SaleEntity sale);
}
