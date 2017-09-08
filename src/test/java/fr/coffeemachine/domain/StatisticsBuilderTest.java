package fr.coffeemachine.domain;

import fr.coffeemachine.domain.drinks.Coffee;
import fr.coffeemachine.domain.drinks.OrangeJuice;
import fr.coffeemachine.domain.drinks.Tea;
import fr.coffeemachine.domain.statistics.Sale;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.StatisticsPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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
    Sale coffeeSale = new Sale(dateService.getTodayLocaleDate(), new Coffee());
    Sale teaSale = new Sale(dateService.getTodayLocaleDate(), new Tea());
    Sale otherTeaSale = new Sale(dateService.getTodayLocaleDate(), new Tea());
    Sale orangeJuiceSale = new Sale(dateService.getTodayLocaleDate(), new OrangeJuice());
    given(saleRepository.getSalesOf(dateService.getTodayDate())).willReturn(Arrays.asList(coffeeSale, teaSale, otherTeaSale, orangeJuiceSale));

    statisticsBuilder.printStatistics(console);

    Map<String, Integer> sales = new HashMap<>();
    sales.put("coffee", 1);
    sales.put("tea", 2);
    sales.put("orange juice", 1);
    verify(console).collectForPrint(sales);
  }
}
