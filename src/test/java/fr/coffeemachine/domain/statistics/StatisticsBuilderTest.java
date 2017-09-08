package fr.coffeemachine.domain.statistics;

import fr.coffeemachine.domain.SaleRepository;
import fr.coffeemachine.domain.drinks.*;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.view.StatisticsPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static fr.coffeemachine.domain.drinks.DrinkEnum.InternalDrinkEnum.COFFEE;
import static fr.coffeemachine.domain.drinks.DrinkEnum.InternalDrinkEnum.ORANGE_JUICE;
import static fr.coffeemachine.domain.drinks.DrinkEnum.InternalDrinkEnum.TEA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsBuilderTest {
  @Mock
  private StatisticsPrinter console;
  private StatisticsBuilder statisticsBuilder;
  @Mock
  private SaleRepository saleRepository;
  @Mock
  private DateService dateService;

  @Before
  public void setUp() throws Exception {
    statisticsBuilder = new CoffeeMachineStatisticsBuilder(saleRepository, dateService);
  }

  @Test
  public void should_build_statistics_for_current_day() throws Exception {
    Sale coffeeSale = new Sale(dateService.getTodayLocaleDate(), new DrinkEnum(COFFEE));
    Sale teaSale = new Sale(dateService.getTodayLocaleDate(), new DrinkEnum(TEA));
    Sale otherTeaSale = new Sale(dateService.getTodayLocaleDate(), new DrinkEnum(TEA));
    Sale orangeJuiceSale = new Sale(dateService.getTodayLocaleDate(), new DrinkEnum(ORANGE_JUICE));
    given(saleRepository.getSalesOf(dateService.getTodayDate())).willReturn(Arrays.asList(coffeeSale, teaSale, otherTeaSale, orangeJuiceSale));

    statisticsBuilder.printStatisticsOfToday(console);

    Map<DrinkEnum, Quantity> sales = new HashMap<>();
    sales.put(new DrinkEnum(COFFEE), new Quantity(1));
    sales.put(new DrinkEnum(TEA), new Quantity(2));
    sales.put(new DrinkEnum(ORANGE_JUICE), new Quantity(1));
    verify(console).collectForPrint(sales);
  }
}
