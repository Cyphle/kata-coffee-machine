package fr.coffeemachine.domain.order;

import fr.coffeemachine.domain.drinks.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.coffeemachine.domain.drinks.DrinkEnum.InternalDrinkEnum.CHOCOLATE;
import static fr.coffeemachine.domain.drinks.DrinkEnum.InternalDrinkEnum.COFFEE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderProcessorTest {
  private OrderProcessor orderProcessor;

  @Before
  public void setUp() throws Exception {
    orderProcessor = new CoffeeMachineOrderProcessor();
  }

  @Test
  public void should_send_empty_string_when_nothing_has_been_ordered() throws Exception {
    assertThat(orderProcessor.createOrderOf(null)).isEqualTo("");
  }

  @Test
  public void should_send_order_of_coffee_with_sugar_and_message_when_ordering_a_coffee_with_one_sugar() throws Exception {
    DrinkEnum drink = new DrinkEnum(COFFEE);
    drink.withSugar(1);

    assertThat(orderProcessor.createOrderOf(drink)).isEqualTo("C:1:0 M:Drink maker makes 1 coffee with 1 sugar and a stick");
  }

  @Test
  public void should_send_order_of_chocolate_without_sugar_nor_stick_when_ordering_a_chocolate_without_sugar() throws Exception {
    assertThat(orderProcessor.createOrderOf(new DrinkEnum(CHOCOLATE))).isEqualTo("H:: M:Drink maker makes 1 chocolate with no sugar - and therefore no stick");
  }
}
