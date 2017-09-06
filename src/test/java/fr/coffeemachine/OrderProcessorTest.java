package fr.coffeemachine;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderProcessorTest {
  private OrderProcessor orderProcessor;

  @Before
  public void setUp() throws Exception {
    orderProcessor = new CoffeeMachineOrderProcessor();
  }

  @Test
  public void should_send_empty_string_when_nothing_has_been_ordered() throws Exception {
    assertThat(orderProcessor.order(null)).isEqualTo("");
  }

  @Test
  public void should_send_coffee_order_when_a_coffee_has_been_ordered() throws Exception {
    assertThat(orderProcessor.order(new Coffee())).isEqualTo("C");
  }

  @Test
  public void should_send_tea_order_when_a_tea_has_been_ordered() throws Exception {
    assertThat(orderProcessor.order(new Tea())).isEqualTo("T");
  }

  @Test
  public void should_send_chocolate_order_when_a_chocolate_has_been_ordered() throws Exception {
    assertThat(orderProcessor.order(new Chocolate())).isEqualTo("H");
  }

  @Test
  public void should_send_coffee_with_one_sugar_when_ordering_a_coffee_with_a_sugar() throws Exception {
    Drink coffeeWithSugar = new Coffee();
    coffeeWithSugar.addSugar(1);
    assertThat(orderProcessor.order(coffeeWithSugar)).isEqualTo("C:1");
  }
}
