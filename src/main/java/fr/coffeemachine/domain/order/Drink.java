package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.utils.Money;
import fr.coffeemachine.domain.utils.Quantity;

import java.math.BigDecimal;
import java.util.Arrays;

import static fr.coffeemachine.domain.utils.Money.money;
import static fr.coffeemachine.domain.order.Drink.AvailableDrink.ORANGE_JUICE;

public class Drink implements Comparable<Drink> {
  private int sugarNumber = 0;
  private boolean isExtraHot = false;
  private final AvailableDrink drink;

  public Drink(AvailableDrink drink) {
    this.drink = drink;
  }

  public boolean isEnoughToPay(Money money) {
    return drink.isEnoughToPay(money);
  }

  public boolean isEmpty() {
    return drink.isEmpty();
  }

  public String getDrink() {
    return drink.toString();
  }

  public String getDrinkName() {
    return drink.getDrinkName();
  }

  public BigDecimal getPrice() {
    return drink.getPrice();
  }

  public Money getPriceInMoney() {
    return drink.price;
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
  public int compareTo(Drink o) {
    return drink.toString().compareTo(o.drink.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Drink drink = (Drink) o;

    if (sugarNumber != drink.sugarNumber) return false;
    if (isExtraHot != drink.isExtraHot) return false;
    return this.drink == drink.drink;
  }

  @Override
  public int hashCode() {
    int result = sugarNumber;
    result = 31 * result + (isExtraHot ? 1 : 0);
    result = 31 * result + (drink != null ? drink.hashCode() : 0);
    return result;
  }

  public void decreaseNumberAvailableDrink() {
    drink.decreaseNumberAvailableDrink();
  }

  public enum AvailableDrink {
    COFFEE("C", money.of(0.4).build(), new Quantity(10)),
    TEA("T", money.of(0.4).build(), new Quantity(10)),
    CHOCOLATE("H", money.of(0.4).build(), new Quantity(1)),
    ORANGE_JUICE("O", money.of(0.6).build(), new Quantity(10));

    final String code;
    final Money price;
    final Quantity quantity;

    AvailableDrink(String code, Money price, Quantity quantity) {
      this.code = code;
      this.price = price;
      this.quantity = quantity;
    }

    boolean isEmpty() {
      return quantity.isZero();
    }

    String getDrinkName() {
      return Arrays.stream(toString().split("_"))
              .reduce("", (namePart, namePartTwo) -> namePart + " " + namePartTwo)
              .trim()
              .toLowerCase();
    }

    BigDecimal getPrice() {
      return price.getAmount();
    }

    Money calculateMissingMoney(Money paidAmount) {
      return price.subtract(paidAmount);
    }

    boolean isEnoughToPay(Money paidAmount) {
      return calculateMissingMoney(paidAmount).isInferiorOrEqualAs(money.of(0).build());
    }

    void decreaseNumberAvailableDrink() {
      quantity.decreaseBy(1);
    }
  }
}
