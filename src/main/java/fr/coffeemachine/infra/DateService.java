package fr.coffeemachine.infra;

import java.time.LocalDate;
import java.util.Date;

public interface DateService {
  Date getTodayDate();

  LocalDate getTodayLocaleDate();
}
