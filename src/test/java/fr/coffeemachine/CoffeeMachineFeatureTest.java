package fr.coffeemachine;

import fr.coffeemachine.drinkmaker.DrinkMaker;
import fr.coffeemachine.drinks.Coffee;
import fr.coffeemachine.drinks.Drink;
import fr.coffeemachine.drinks.OrangeJuice;
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

  @Test
  public void should_send_a_charged_orange_juice_when_there_is_enough_money() throws Exception {
    Drink orangeJuice = new OrangeJuice();

    machine.orderChargedDrinkOf(orangeJuice, money.of(0.6).build());

    verify(drinkMaker).takeOrderOf("O:: M:Drink maker makes 1 orange juice");
  }

  @Test
  public void should_not_send_charged_order_for_orange_juice_if_not_enough_money() throws Exception {
    machine.orderChargedDrinkOf(new OrangeJuice(), money.of(0.3).build());

    verify(drinkMaker).takeOrderOf("M:Order for 1 orange juice at 0.60 euros is missing 0.30 euros");
  }

  @Test
  public void should_send_charge_coffee_extra_hot_when_there_is_enough_money_and_is_asked_extra_hot() throws Exception {
    Drink coffee = new Coffee();
    coffee.addSugar(1);
    coffee.setExtraHot();

    machine.orderChargedDrinkOf(coffee, money.of(0.4).build());

    verify(drinkMaker).takeOrderOf("Ch:1:0 M:Drink maker makes 1 coffee with 1 sugar and a stick");
  }

  @Test
  public void should_send_charge_orange_juice_and_not_add_extra_hot_when_there_is_enough_money_and_is_asked_extra_hot() throws Exception {
    Drink orangeJuice = new OrangeJuice();
    orangeJuice.setExtraHot();

    machine.orderChargedDrinkOf(orangeJuice, money.of(0.6).build());

    verify(drinkMaker).takeOrderOf("O:: M:Drink maker makes 1 orange juice");
  }
}
