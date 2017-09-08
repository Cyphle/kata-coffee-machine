package fr.coffeemachine.infra.repositories;

import fr.coffeemachine.domain.statistics.Sale;
import fr.coffeemachine.infra.entities.SaleEntity;

import java.util.List;

public interface DBSaleRepository {
  SaleEntity save(SaleEntity sale);

  List<SaleEntity> findAll();
}
