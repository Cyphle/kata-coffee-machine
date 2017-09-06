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

  @Test
  public void should_send_drink_with_two_sugars_when_ordering_a_drink() throws Exception {
    Drink teaWithSugars = new Tea();
    teaWithSugars.addSugar(2);
    assertThat(orderProcessor.order(teaWithSugars)).isEqualTo("T:2");
  }

  @Test
  public void should_return_an_order_status_with_too_much_sugar_when_trying_to_add_three_sugars() throws Exception {
    Drink chocolateWithSugar = new Chocolate();
    assertThat(chocolateWithSugar.addSugar(3)).isEqualTo(OrderStatus.TOO_MUCH_SUGAR);
  }

  @Test
  public void should_send_coffee_with_one_sugar_and_a_stick_when_ordering_a_coffee_with_one_sugar() throws Exception {
    Drink coffeeWithSugar = new Coffee();
    coffeeWithSugar.addSugar(1);
    assertThat(orderProcessor.orderDrink(coffeeWithSugar)).isEqualTo("C:1:0");
  }
}
