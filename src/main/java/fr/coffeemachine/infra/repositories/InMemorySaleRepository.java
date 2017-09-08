package fr.coffeemachine.infra.repositories;

import fr.coffeemachine.infra.entities.SaleEntity;

public class InMemorySaleRepository implements DBSaleRepository {
  @Override
  public SaleEntity save(SaleEntity sale) {
    throw new UnsupportedOperationException();
  }
}
