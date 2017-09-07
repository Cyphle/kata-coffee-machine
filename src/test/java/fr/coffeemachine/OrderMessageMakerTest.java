package fr.coffeemachine;

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
    assertThat(messageMaker.makeMessageForOrderOf(coffee)).isEqualTo(new OrderMessage("Drink maker makes 1 chocolate with no sugar - and therefore no stick"));
  }
}
