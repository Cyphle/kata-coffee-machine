package fr.coffeemachine;

import fr.coffeemachine.drinks.Chocolate;
import fr.coffeemachine.drinks.Coffee;
import fr.coffeemachine.drinks.Drink;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMessageMakerTest {
  private MessageMaker messageMaker;

  @Before
  public void setUp() throws Exception {
    messageMaker = new OrderMessageMaker();
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
}
