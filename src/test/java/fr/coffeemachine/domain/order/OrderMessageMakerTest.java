package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.drinks.Chocolate;
import fr.coffeemachine.domain.drinks.Coffee;
import fr.coffeemachine.domain.drinks.Drink;
import org.junit.Before;
import org.junit.Test;

import static fr.coffeemachine.domain.Money.money;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderMessageMakerTest {
  private MessageMaker messageMaker;

  @Before
  public void setUp() throws Exception {
    messageMaker = new CoffeeMachineMessageMaker();
  }

  @Test
  public void should_make_message_for_a_coffee_without_sugar() throws Exception {
    Drink coffee = new Coffee();
    assertThat(messageMaker.makeMessageForOrderOf(coffee)).isEqualTo(new OrderMessage("Drink maker makes 1 coffee with no sugar - and therefore no stick"));
  }

  @Test
  public void should_make_message_for_a_coffee_with_one_sugar_and_a_stick() throws Exception {
    Drink coffeeWithOneSugar = new Coffee();
    coffeeWithOneSugar.addSugar(1);
    assertThat(messageMaker.makeMessageForOrderOf(coffeeWithOneSugar)).isEqualTo(new OrderMessage("Drink maker makes 1 coffee with 1 sugar and a stick"));
  }

  @Test
  public void should_make_message_for_a_chocolate_with_two_sugars_and_a_stick() throws Exception {
    Drink chocolateWithTwoSugars = new Chocolate();
    chocolateWithTwoSugars.addSugar(2);
    assertThat(messageMaker.makeMessageForOrderOf(chocolateWithTwoSugars)).isEqualTo(new OrderMessage("Drink maker makes 1 chocolate with 2 sugars and a stick"));
  }

  @Test
  public void should_make_message_for_not_enough_money_when_ordering_coffee_with_not_enough_money() throws Exception {
    assertThat(messageMaker.makeNotEnoughMoneyMessage(new Coffee(), money.of(0.2).build())).isEqualTo(new OrderMessage("Order for 1 coffee at 0.40 euros is missing 0.20 euros"));
  }
}
