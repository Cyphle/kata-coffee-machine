package fr.coffeemachine;

import fr.coffeemachine.drinkmaker.DrinkMaker;
import fr.coffeemachine.order.OrderProcessor;
import fr.coffeemachine.order.drinks.Coffee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {
  @Mock
  private DrinkMaker drinkMaker;
  @Mock
  private OrderProcessor orderProcessor;
  private DrinkMachine machine;

  @Before
  public void setUp() throws Exception {
    machine = new CoffeeMachine(drinkMaker, orderProcessor);
    Mockito.doNothing().when(drinkMaker).makeDrinkFromOrder(any(String.class));
  }

  @Test
  public void should_send_an_order_of_a_coffee_with_no_sugar_to_drink_maker() throws Exception {
    given(orderProcessor.orderDrink(new Coffee())).willReturn("C::");

    machine.orderDrinkOf(new Coffee());

    verify(drinkMaker).makeDrinkFromOrder("C::");
  }
}
