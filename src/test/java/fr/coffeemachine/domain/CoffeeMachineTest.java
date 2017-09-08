package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinkmaker.DrinkMaker;
import fr.coffeemachine.domain.drinks.Coffee;
import fr.coffeemachine.domain.drinks.Drink;
import fr.coffeemachine.domain.order.OrderProcessor;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.coffeemachine.domain.Money.money;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {
  @Mock
  private DrinkMaker drinkMaker;
  @Mock
  private OrderProcessor orderProcessor;
  private CoffeeMachine machine;
  @Mock
  private SaleRepository saleRepository;

  @Before
  public void setUp() throws Exception {
    machine = new CoffeeMachine(drinkMaker, orderProcessor, saleRepository);
  }

  @Test
  public void should_save_order_in_repository_if_enough_money() throws Exception {
    Drink coffee = new Coffee();

    machine.orderChargedDrinkOf(coffee, money.of(0.4).build());

    verify(drinkMaker).takeOrderOf(orderProcessor.makeOrderWithMessage(new Coffee()));
    verify(saleRepository).addSell(coffee);
  }

  @Test
  public void should_not_save_order_if_not_enough_money() throws Exception {
    Drink coffee = new Coffee();

    machine.orderChargedDrinkOf(coffee, money.of(0.2).build());

    verify(saleRepository, never()).addSell(coffee);
  }

  @Test
  public void should_send_a_message_that_there_is_a_beverage_shortage_to_drink_maker_so_than_it_can_forward_it_to_customer() throws Exception {
    given(orderProcessor.makeOrderWithBeverageShortage(new Coffee())).willReturn("M:Sorry but coffee is not available at the moment");
    Drink coffee = new Coffee();

    machine.orderChargedDrinkOf(coffee, money.of(0.4).build());

    verify(drinkMaker).takeOrderOf("M:Sorry but coffee is not available at the moment");
  }

  @Test
  public void should_be_true_if_coffee_is_empty() throws Exception {
    assertThat(machine.isEmpty(new Coffee())).isTrue();
  }
}
