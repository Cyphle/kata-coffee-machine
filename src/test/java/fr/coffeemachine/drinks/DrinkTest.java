package fr.coffeemachine.drinks;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DrinkTest {
  @Test
  public void should_return_an_order_status_with_too_much_sugar_when_trying_to_add_three_sugars() throws Exception {
    Drink chocolateWithSugar = new Chocolate();
    assertThat(chocolateWithSugar.addSugar(3)).isEqualTo(SugarStatus.TOO_MUCH_SUGAR);
  }
}
