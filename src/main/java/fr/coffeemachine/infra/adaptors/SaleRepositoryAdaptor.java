package fr.coffeemachine.infra.adaptors;

import fr.coffeemachine.domain.SaleRepository;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.statistics.Sale;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.entities.SaleEntity;
import fr.coffeemachine.infra.repositories.DBSaleRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SaleRepositoryAdaptor implements SaleRepository {
  private DBSaleRepository dbSaleRepository;
  private DateService dateService;

  public SaleRepositoryAdaptor(DBSaleRepository dbSaleRepository, DateService dateService) {
    this.dbSaleRepository = dbSaleRepository;
    this.dateService = dateService;
  }

  @Override
  public void addSell(Drink drink) {
    SaleEntity sale = SaleEntity.fromDomainToEntity(drink);
    sale.setSellingDate(dateService.getTodayDate());
    dbSaleRepository.save(sale);
  }

  @Override
  public List<Sale> getSalesOf(Date requiredDate) {
    return dbSaleRepository.findAll()
            .stream()
            .filter(sale -> sale.getSellingDate().equals(requiredDate))
            .map(SaleEntity::fromEntityToDomain)
            .collect(Collectors.toList());
  }
}
