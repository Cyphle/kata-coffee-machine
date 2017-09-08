package fr.coffeemachine.domain.statistics;

public class Quantity {
  private final int quantity;

  public Quantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Quantity quantity1 = (Quantity) o;

    return quantity == quantity1.quantity;
  }

  @Override
  public int hashCode() {
    return quantity;
  }
}
