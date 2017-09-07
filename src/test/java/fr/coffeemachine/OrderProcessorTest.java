package fr.coffeemachine;

import fr.coffeemachine.drinks.Chocolate;
import fr.coffeemachine.drinks.Coffee;
import fr.coffeemachine.drinks.Drink;
import fr.coffeemachine.drinks.Tea;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderProcessorTest {
  private OrderProcessor orderProcessor;
  @Mock
  private OrderMaker orderMaker;
  @Mock
  private MessageMaker orderMessageMaker;

  @Before
  public void setUp() throws Exception {
    orderProcessor = new CoffeeMachineOrderProcessor(orderMaker, orderMessageMaker);
  }

  @Test
  public void should_send_empty_string_when_nothing_has_been_ordered() throws Exception {
    assertThat(orderProcessor.orderDrink(null)).isEqualTo("");
  }

  @Test
  public void should_send_coffee_order_when_a_coffee_has_been_ordered() throws Exception {
    given(orderMaker.orderDrink(new Coffee())).willReturn("C::");

    assertThat(orderProcessor.orderDrink(new Coffee())).isEqualTo("C::");
    verify(orderMaker).orderDrink(new Coffee());
  }

  @Test
  public void should_send_tea_order_when_a_tea_has_been_ordered() throws Exception {
    given(orderMaker.orderDrink(new Tea())).willReturn("T::");

    assertThat(orderProcessor.orderDrink(new Tea())).isEqualTo("T::");
    verify(orderMaker).orderDrink(new Tea());
  }

  @Test
  public void should_send_chocolate_order_when_a_chocolate_has_been_ordered() throws Exception {
    given(orderMaker.orderDrink(new Chocolate())).willReturn("H::");

    assertThat(orderProcessor.orderDrink(new Chocolate())).isEqualTo("H::");
    verify(orderMaker).orderDrink(new Chocolate());
  }

  @Test
  public void should_send_coffee_with_one_sugar_when_ordering_a_coffee_with_a_sugar() throws Exception {
    Drink coffeeWithSugar = new Coffee();
    coffeeWithSugar.addSugar(1);
    given(orderMaker.orderDrink(coffeeWithSugar)).willReturn("C:1:0");

    assertThat(orderProcessor.orderDrink(coffeeWithSugar)).isEqualTo("C:1:0");
    verify(orderMaker).orderDrink(coffeeWithSugar);
  }

  @Test
  public void should_send_drink_with_two_sugars_when_ordering_a_drink() throws Exception {
    Drink teaWithSugars = new Tea();
    teaWithSugars.addSugar(2);
    given(orderMaker.orderDrink(teaWithSugars)).willReturn("T:2:0");

    assertThat(orderProcessor.orderDrink(teaWithSugars)).isEqualTo("T:2:0");
    verify(orderMaker).orderDrink(teaWithSugars);
  }

  @Test
  public void should_send_order_of_coffee_with_sugar_and_message_when_ordering_a_coffee_with_one_sugar() throws Exception {
    Drink coffeeWithSugar = new Coffee();
    coffeeWithSugar.addSugar(1);
    given(orderMessageMaker.makeMessageForOrderOf(coffeeWithSugar)).willReturn(new OrderMessage("Drink maker makes 1 tea with 1 sugar and a stick"));
    given(orderMaker.orderDrink(coffeeWithSugar)).willReturn("C:1:0");

    assertThat(orderProcessor.orderWithMessage(coffeeWithSugar)).isEqualTo("C:1:0 M:Drink maker makes 1 tea with 1 sugar and a stick");
    verify(orderMessageMaker).makeMessageForOrderOf(coffeeWithSugar);
    verify(orderMaker).orderDrink(coffeeWithSugar);
  }

  @Test
  public void should_send_order_of_chocolate_without_sugar_nor_stick_when_ordering_a_chocolate_without_sugar() throws Exception {
    Drink chocolate = new Chocolate();
    given(orderMessageMaker.makeMessageForOrderOf(chocolate)).willReturn(new OrderMessage("Drink maker makes 1 chocolate with no sugar - and therefore no stick"));
    given(orderMaker.orderDrink(chocolate)).willReturn("H::");

    assertThat(orderProcessor.orderWithMessage(chocolate)).isEqualTo("H:: M:Drink maker makes 1 chocolate with no sugar - and therefore no stick");
    verify(orderMessageMaker).makeMessageForOrderOf(chocolate);
    verify(orderMaker).orderDrink(chocolate);
  }
}
