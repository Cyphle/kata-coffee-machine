package fr.coffeemachine.domain.drinks;

import fr.coffeemachine.domain.Money;
import fr.coffeemachine.domain.statistics.Quantity;

import java.math.BigDecimal;
import java.util.Arrays;

import static fr.coffeemachine.domain.Money.money;
import static fr.coffeemachine.domain.drinks.DrinkEnum.InternalDrinkEnum.ORANGE_JUICE;

public class DrinkEnum {
  private int sugarNumber = 0;
  private boolean isExtraHot = false;
  private InternalDrinkEnum drink;

  public DrinkEnum(InternalDrinkEnum drink) {
    this.drink = drink;
  }

  public boolean isEnoughToPay(Money money) {
    return drink.isEnoughToPay(money);
  }

  public boolean isEmpty() {
    return drink.isEmpty();
  }

  public String getDrinkName() {
    return drink.getDrinkName();
  }

  public BigDecimal getPrice() {
    return drink.getPrice();
  }

  public String getTypeAndTemperature() {
    return drink.code + (isExtraHot ? "h" : "");
  }

  public Money calculateMissingMoney(Money paidAmount) {
    return drink.calculateMissingMoney(paidAmount);
  }

  public void withSugar(int numberOfSugar) {
    if (canHaveSugarAndBeExtraHot())
      this.sugarNumber = numberOfSugar;
  }

  public int getNumberOfSugars() {
    return sugarNumber;
  }

  public boolean canHaveSugarAndBeExtraHot() {
    return drink != ORANGE_JUICE;
  }

  public void setExtraHot() {
    if (canHaveSugarAndBeExtraHot())
      this.isExtraHot = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DrinkEnum drinkEnum = (DrinkEnum) o;

    if (sugarNumber != drinkEnum.sugarNumber) return false;
    if (isExtraHot != drinkEnum.isExtraHot) return false;
    return drink == drinkEnum.drink;
  }

  @Override
  public int hashCode() {
    int result = sugarNumber;
    result = 31 * result + (isExtraHot ? 1 : 0);
    result = 31 * result + (drink != null ? drink.hashCode() : 0);
    return result;
  }

  public enum InternalDrinkEnum {
    COFFEE("C", money.of(0.4).build(), new Quantity(10)),
    TEA("T", money.of(0.4).build(), new Quantity(10)),
    CHOCOLATE("H", money.of(0.4).build(), new Quantity(1)),
    ORANGE_JUICE("O", money.of(0.4).build(), new Quantity(10));

    public final String code;
    public final Money price;
    public Quantity quantity;

    InternalDrinkEnum(String code, Money price, Quantity quantity) {
      this.code = code;
      this.price = price;
      this.quantity = quantity;
    }

    public boolean isEmpty() {
      return quantity.isZero();
    }

    public String getDrinkName() {
      return Arrays.asList(toString().split("_"))
              .stream()
              .reduce("", (namePart, namePartTwo) -> namePart + " " + namePartTwo)
              .trim()
              .toLowerCase();
    }

    public BigDecimal getPrice() {
      return price.getAmount();
    }

    public Money calculateMissingMoney(Money paidAmount) {
      return price.subtract(paidAmount);
    }

    public boolean isEnoughToPay(Money paidAmount) {
      return calculateMissingMoney(paidAmount).isInferiorOrEqualAs(money.of(0).build());
    }
  }
}
