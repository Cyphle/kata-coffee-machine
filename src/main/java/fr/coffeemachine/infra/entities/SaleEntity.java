package fr.coffeemachine.infra.entities;

import fr.coffeemachine.domain.drinks.Drink;

import java.util.Date;

public class SaleEntity {
  private Date sellingDate;
  private String drinkName;

  public Date getSellingDate() {
    return sellingDate;
  }

  public void setSellingDate(Date sellingDate) {
    this.sellingDate = sellingDate;
  }

  public String getDrinkName() {
    return drinkName;
  }

  public void setDrinkName(String drinkName) {
    this.drinkName = drinkName;
  }

  public static SaleEntity fromDomainToEntity(Drink drink) {
    SaleEntity sale = new SaleEntity();
    sale.setDrinkName(drink.getDrinkName());
    return sale;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SaleEntity that = (SaleEntity) o;

    if (sellingDate != null ? !sellingDate.equals(that.sellingDate) : that.sellingDate != null) return false;
    return drinkName != null ? drinkName.equals(that.drinkName) : that.drinkName == null;
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
