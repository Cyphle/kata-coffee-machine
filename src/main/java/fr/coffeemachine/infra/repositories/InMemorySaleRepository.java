package fr.coffeemachine.infra.repositories;

import fr.coffeemachine.infra.entities.SaleEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemorySaleRepository implements DBSaleRepository {
  private final List<SaleEntity> sales;

  public InMemorySaleRepository() {
    sales = new ArrayList<>();
  }

  @Override
  public void save(SaleEntity sale) {
    sales.add(sale);
  }

  @Override
  public List<SaleEntity> findAll() {
    return sales;
  }
}
