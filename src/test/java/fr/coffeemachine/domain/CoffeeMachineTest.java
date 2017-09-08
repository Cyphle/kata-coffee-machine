package fr.coffeemachine.domain;

import fr.coffeemachine.domain.notification.EmailSender;
import fr.coffeemachine.domain.order.Drink;
import fr.coffeemachine.domain.order.OrderProcessor;
import fr.coffeemachine.domain.statistics.SaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.coffeemachine.domain.utils.Money.money;
import static fr.coffeemachine.domain.order.Drink.AvailableDrink.COFFEE;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {
  @Mock
  private OrderProcessor orderProcessor;
  @Mock
  private SaleRepository saleRepository;
  @Mock
  private EmailSender emailSender;
  private CoffeeMachine machine;

  @Before
  public void setUp() throws Exception {
    machine = new CoffeeMachine(orderProcessor, saleRepository, emailSender);
  }

  @Test
  public void should_save_order_in_repository_if_enough_money() throws Exception {
    machine.orderDrinkOf(new Drink(COFFEE), money.of(0.4).build());

    verify(saleRepository).addSell(new Drink(COFFEE));
  }

  @Test
  public void should_not_save_order_if_not_enough_money() throws Exception {
    machine.orderDrinkOf(new Drink(COFFEE), money.of(0.2).build());

    verify(saleRepository, never()).addSell(new Drink(COFFEE));
  }
}
