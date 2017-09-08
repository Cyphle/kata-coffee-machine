package fr.coffeemachine.domain.drinks;

import org.junit.Test;

import static fr.coffeemachine.domain.drinks.SugarStatus.SUGAR_NOT_ALLOWED_FOR_THIS_DRINK;
import static fr.coffeemachine.domain.drinks.SugarStatus.TOO_MUCH_SUGAR;
import static org.assertj.core.api.Assertions.assertThat;

public class DrinkTest {
  @Test
  public void should_return_an_order_status_with_too_much_sugar_when_trying_to_add_three_sugars() throws Exception {
    Drink chocolateWithSugar = new Chocolate();
    assertThat(chocolateWithSugar.addSugar(3)).isEqualTo(TOO_MUCH_SUGAR);
  }

  @Test
  public void should_return_a_drink_status_saying_that_cannot_add_sugar_if_drink_cannot_have_more_sugar() throws Exception {
    Drink orangeJuice = new OrangeJuice();
    assertThat(orangeJuice.addSugar(3)).isEqualTo(SUGAR_NOT_ALLOWED_FOR_THIS_DRINK);
  }
}
