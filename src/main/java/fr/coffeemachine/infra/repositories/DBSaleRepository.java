package fr.coffeemachine.infra.repositories;

import fr.coffeemachine.infra.entities.SaleEntity;

import java.util.List;

public interface DBSaleRepository {
  void save(SaleEntity sale);

  List<SaleEntity> findAll();
}
