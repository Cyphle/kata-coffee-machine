package fr.coffeemachine;

public class OrderMessage {
  private String message;

  public OrderMessage(String message) {
    this.message = message;
  }

  public String getMessageForDrinkMaker() {
    return "M:" + message;
  }
}
