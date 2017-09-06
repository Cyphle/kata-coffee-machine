package fr.coffeemachine;

abstract class Drink {
  private int numberOfSugars;

  abstract String getDrinkType();

  public int getNumberOfSugars() {
    return numberOfSugars;
  }

  public void addSugar(int numberOfSugars) {
    this.numberOfSugars = numberOfSugars;
  }
}
