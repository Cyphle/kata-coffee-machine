package fr.coffeemachine.infra.entities;

import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.drinks.DrinkEnum;
import fr.coffeemachine.domain.drinks.DrinkFactory;
import fr.coffeemachine.domain.statistics.Sale;

import java.time.ZoneId;
import java.util.Date;

public class SaleEntity {
  private Date sellingDate;
  private String drinkName;

  public SaleEntity() {
  }

  public Date getSellingDate() {
    return sellingDate;
  }

  public void setSellingDate(Date sellingDate) {
    this.sellingDate = sellingDate;
  }

  public void setDrinkName(String drinkName) {
    this.drinkName = drinkName;
  }

  public Sale fromEntityToDomain() {
    return new Sale(sellingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), DrinkFactory.makeDrink(drinkName));
  }

  public static SaleEntity fromDomainToEntity(Drink drink) {
    SaleEntity sale = new SaleEntity();
    sale.setDrinkName(drink.getDrinkName());
    return sale;
  }

  public static SaleEntity fromDomainToEntity(DrinkEnum drink) {
    SaleEntity sale = new SaleEntity();
    sale.setDrinkName(drink.getDrinkName());
    return sale;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SaleEntity that = (SaleEntity) o;

    return (sellingDate != null ? sellingDate.equals(that.sellingDate) : that.sellingDate == null) && (drinkName != null ? drinkName.equals(that.drinkName) : that.drinkName == null);
  }

  @Override
  public int hashCode() {
    int result = sellingDate != null ? sellingDate.hashCode() : 0;
    result = 31 * result + (drinkName != null ? drinkName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SaleEntity{" +
            "sellingDate=" + sellingDate +
            ", drinkName='" + drinkName + '\'' +
            '}';
  }
}
