package fr.coffeemachine.infra.adaptors;

import fr.coffeemachine.domain.SaleRepository;
import fr.coffeemachine.domain.drinks.Coffee;
import fr.coffeemachine.infra.DateService;
import fr.coffeemachine.infra.entities.SaleEntity;
import fr.coffeemachine.infra.repositories.DBSaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaleRepositoryAdaptorTest {
  private SaleRepository saleRepository;
  @Mock
  private DBSaleRepository dbSellRepository;
  @Mock
  private DateService dateService;

  @Before
  public void setUp() throws Exception {
    saleRepository = new SaleRepositoryAdaptor(dbSellRepository, dateService);
    given(dateService.getTodayDate()).willReturn(Date.from(LocalDate.of(2017, Month.SEPTEMBER, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()));
  }

  @Test
  public void should_save_a_sell_of_drink_in_repository() throws Exception {
    LocalDate sellingLocaleDate = LocalDate.of(2017, Month.SEPTEMBER, 8);
    Date sellingDate = Date.from(sellingLocaleDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    saleRepository.addSell(new Coffee());

    SaleEntity coffeeSale = new SaleEntity();
    coffeeSale.setDrinkName("coffee");
    coffeeSale.setSellingDate(sellingDate);
    verify(dbSellRepository).save(coffeeSale);
  }
}
