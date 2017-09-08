package fr.coffeemachine;

import fr.coffeemachine.drinkmaker.DrinkMaker;
import fr.coffeemachine.drinks.Coffee;
import fr.coffeemachine.drinks.Drink;
import fr.coffeemachine.order.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.coffeemachine.Money.money;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineFeatureTest {
  @Mock
  private DrinkMaker drinkMaker;
  private DrinkMachine machine;

  @Before
  public void setUp() throws Exception {
    OrderMaker orderMaker = new CoffeeMachineOrderMaker();
    MessageMaker orderMessageMaker = new CoffeeMachineMessageMaker();
    OrderProcessor orderProcessor = new CoffeeMachineOrderProcessor(orderMaker, orderMessageMaker);
    machine = new CoffeeMachine(drinkMaker, orderProcessor);
  }

  @Test
  public void should_not_send_a_charged_order_if_not_enough_money_has_been_given_but_send_a_message_to_drink_maker() throws Exception {
    machine.orderChargedDrinkOf(new Coffee(), money.of(0.2).build());

    verify(drinkMaker).takeOrderOf("M:Order for 1 coffee at 0.40 euros is missing 0.20 euros");
  }

  @Test
  public void should_send_a_charged_order_of_coffee_with_one_sugar_if_money_is_enough() throws Exception {
    Drink coffee = new Coffee();
    coffee.addSugar(1);

    machine.orderChargedDrinkOf(coffee, money.of(0.4).build());

    verify(drinkMaker).takeOrderOf("C:1:0 M:Drink maker makes 1 coffee with 1 sugar and a stick");
  }
}
