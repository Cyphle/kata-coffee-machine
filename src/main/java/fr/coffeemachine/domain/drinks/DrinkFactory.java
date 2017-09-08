package fr.coffeemachine.domain.drinks;

public class DrinkFactory {
  public static Drink makeDrink(String drinkType) {
    switch (drinkType) {
      case "coffee":
        return new Coffee();
      case "tea":
        return new Tea();
      case "chocolate":
        return new Chocolate();
      case "orange juice":
        return new OrangeJuice();
      default:
        return new UnknownDrink();
    }
  }
}
