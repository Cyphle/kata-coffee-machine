package fr.coffeemachine;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderProcessorTest {
  @Test
  public void should_send_empty_string_when_nothing_has_been_ordered() throws Exception {
    OrderProcessor orderProcessor = new CoffeMachineOrderProcessor();
    assertThat(orderProcessor.sendInstructions()).isEqualTo("");
  }
}
