package fr.coffeemachine;

import fr.coffeemachine.drinks.Chocolate;
import fr.coffeemachine.drinks.Coffee;
import fr.coffeemachine.drinks.Drink;
import fr.coffeemachine.drinks.Tea;
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
    assertThat(orderProcessor.orderDrink(null)).isEqualTo("");
  }

  @Test
  public void should_send_coffee_order_when_a_coffee_has_been_ordered() throws Exception {
    assertThat(orderProcessor.orderDrink(new Coffee())).isEqualTo("C::");
  }

  @Test
  public void should_send_tea_order_when_a_tea_has_been_ordered() throws Exception {
    assertThat(orderProcessor.orderDrink(new Tea())).isEqualTo("T::");
  }

  @Test
  public void should_send_chocolate_order_when_a_chocolate_has_been_ordered() throws Exception {
    assertThat(orderProcessor.orderDrink(new Chocolate())).isEqualTo("H::");
  }

  @Test
  public void should_send_coffee_with_one_sugar_when_ordering_a_coffee_with_a_sugar() throws Exception {
    Drink coffeeWithSugar = new Coffee();
    coffeeWithSugar.addSugar(1);
    assertThat(orderProcessor.orderDrink(coffeeWithSugar)).isEqualTo("C:1:0");
  }

  @Test
  public void should_send_drink_with_two_sugars_when_ordering_a_drink() throws Exception {
    Drink teaWithSugars = new Tea();
    teaWithSugars.addSugar(2);
    assertThat(orderProcessor.orderDrink(teaWithSugars)).isEqualTo("T:2:0");
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

  @Test
  public void should_send_order_of_coffee_with_sugar_and_message_when_ordering_a_coffee_with_one_sugar() throws Exception {
    Drink coffeeWithSugar = new Coffee();
    coffeeWithSugar.addSugar(1);
    assertThat(orderProcessor.orderWithMessage(coffeeWithSugar)).isEqualTo("C:1:0 M:Drink maker makes 1 tea with 1 sugar and a stick");
  }

  @Test
  public void should_send_order_of_chocolate_without_sugar_nor_stick_when_ordering_a_chocolate_without_sugar() throws Exception {
    Drink chocolate = new Chocolate();
    assertThat(orderProcessor.orderWithMessage(chocolate)).isEqualTo("H:: M:Drink maker makes 1 chocolate with no sugar - and therefore no stick");
  }
}
