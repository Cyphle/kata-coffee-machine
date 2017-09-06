package fr.coffeemachine;

public class CoffeMachineOrderProcessor implements OrderProcessor {
  @Override
  public String order(Coffee coffee) {
    if (coffee != null)
      return "C";
    return "";
  }
}
