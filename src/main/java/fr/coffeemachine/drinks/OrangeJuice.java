package fr.coffeemachine.drinks;

public class OrangeJuice extends Drink {
  @Override
  public String getDrinkType() {
    return "O";
  }

  @Override
  public String getDrinkName() {
    return "orange juice";
  }

  @Override
  public boolean canHaveSugar() {
    return false;
  }
}
