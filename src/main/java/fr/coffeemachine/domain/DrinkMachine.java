package fr.coffeemachine.domain;

interface DrinkMachine {
  String orderDrinkOf(Drink drink, Money money);
}
