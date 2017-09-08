package fr.coffeemachine.domain;

import fr.coffeemachine.domain.notification.EmailSender;
import fr.coffeemachine.domain.order.*;
import fr.coffeemachine.domain.statistics.CoffeeMachineStatisticsBuilder;
import fr.coffeemachine.domain.statistics.SaleRepository;
import fr.coffeemachine.domain.statistics.StatisticsBuilder;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.adaptors.SaleRepositoryAdaptor;
import fr.coffeemachine.infra.repositories.InMemorySaleRepository;
import fr.coffeemachine.infra.view.ConsoleStatisticsPrinter;
import fr.coffeemachine.infra.view.StatisticsPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static fr.coffeemachine.domain.utils.Money.money;
import static fr.coffeemachine.domain.order.Drink.AvailableDrink.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineFeatureTest {
  private StatisticsPrinter console;
  @Mock
  private DateService dateService;
  @Mock
  private EmailSender emailSender;
  private DrinkMachine machine;
  private StatisticsBuilder statisticsBuilder;

  @Before
  public void setUp() throws Exception {
    OrderProcessor orderProcessor = new CoffeeMachineOrderProcessor();
    SaleRepository saleRepository = new SaleRepositoryAdaptor(new InMemorySaleRepository(), dateService);
    machine = new CoffeeMachine(orderProcessor, saleRepository, emailSender);

    statisticsBuilder = new CoffeeMachineStatisticsBuilder(saleRepository, dateService);

    console = new ConsoleStatisticsPrinter();

    given(dateService.getTodayDate()).willReturn(Date.from(LocalDate.of(2017, Month.SEPTEMBER, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()));
  }

  @Test
  public void should_not_send_a_charged_order_if_not_enough_money_has_been_given_but_send_a_message_to_drink_maker() throws Exception {
    String order = machine.orderDrinkOf(new Drink(COFFEE), money.of(0.2).build());

    assertThat(order).isEqualTo("M:Order for 1 coffee at 0.40 euros is missing 0.20 euros");
  }

  @Test
  public void should_send_a_charged_order_of_coffee_with_one_sugar_if_money_is_enough() throws Exception {
    Drink drink = new Drink(COFFEE);
    drink.withSugar(1);

    String order = machine.orderDrinkOf(drink, money.of(0.4).build());

    assertThat(order).isEqualTo("C:1:0 M:Drink maker makes 1 coffee with 1 sugar and a stick");
  }

  @Test
  public void should_send_a_charged_order_of_coffee_with_no_sugar_if_money_is_enough() throws Exception {
    Drink drink = new Drink(COFFEE);

    String order = machine.orderDrinkOf(drink, money.of(0.4).build());

    assertThat(order).isEqualTo("C:: M:Drink maker makes 1 coffee with no sugar - and therefore no stick");
  }

  @Test
  public void should_send_a_charged_orange_juice_when_there_is_enough_money() throws Exception {
    String order = machine.orderDrinkOf(new Drink(ORANGE_JUICE), money.of(0.6).build());

    assertThat(order).isEqualTo("O:: M:Drink maker makes 1 orange juice");
  }

  @Test
  public void should_send_charge_coffee_extra_hot_when_there_is_enough_money_and_is_asked_extra_hot() throws Exception {
    Drink coffee = new Drink(COFFEE);
    coffee.withSugar(2);
    coffee.setExtraHot();
    String order = machine.orderDrinkOf(coffee, money.of(0.4).build());

    assertThat(order).isEqualTo("Ch:2:0 M:Drink maker makes 1 coffee with 2 sugars and a stick");
  }

  @Test
  public void should_send_charge_orange_juice_and_not_add_extra_hot_when_there_is_enough_money_and_is_asked_extra_hot() throws Exception {
    Drink orangeJuice = new Drink(ORANGE_JUICE);
    orangeJuice.setExtraHot();
    String order = machine.orderDrinkOf(orangeJuice, money.of(0.6).build());

    assertThat(order).isEqualTo("O:: M:Drink maker makes 1 orange juice");
  }

  @Test
  public void should_get_statistics_of_sells_when_asked_for() throws Exception {
    machine.orderDrinkOf(new Drink(COFFEE), money.of(0.4).build());
    machine.orderDrinkOf(new Drink(TEA), money.of(0.4).build());
    Drink teaWithSugar = new Drink(TEA);
    teaWithSugar.withSugar(1);
    machine.orderDrinkOf(teaWithSugar, money.of(0.4).build());
    machine.orderDrinkOf(new Drink(ORANGE_JUICE), money.of(0.6).build());

    statisticsBuilder.printStatisticsOfToday(console);

    assertThat(console.flush()).isEqualTo("Sells : 1 coffee - 1 orange juice - 2 teas\nTotal : 1.80 euros");
  }

  @Test
  public void should_send_notification_and_inform_customer_when_beverage_is_no_more_available() throws Exception {
    String order = machine.orderDrinkOf(new Drink(CHOCOLATE), money.of(0.4).build());
    String notAvailableMessage = machine.orderDrinkOf(new Drink(CHOCOLATE), money.of(0.4).build());

    assertThat(order).isEqualTo("H:: M:Drink maker makes 1 chocolate with no sugar - and therefore no stick");
    assertThat(notAvailableMessage).isEqualTo("M:Sorry but chocolate is not available at the moment");
    verify(emailSender).sendBeverageShortageNotification(new Drink(CHOCOLATE));
  }
}
