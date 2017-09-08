package fr.coffeemachine.infra.repositories;

import fr.coffeemachine.infra.entities.SaleEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemorySaleRepository implements DBSaleRepository {
  private List<SaleEntity> sales;

  public InMemorySaleRepository() {
    sales = new ArrayList<>();
  }

  @Override
  public SaleEntity save(SaleEntity sale) {
    sales.add(sale);
    return sale;
  }
}
