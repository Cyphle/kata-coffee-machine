package fr.coffeemachine;

public class OrderMessage {
  private final String message;

  public OrderMessage(String message) {
    this.message = message;
  }

  public String getMessageForDrinkMaker() {
    return "M:" + message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrderMessage that = (OrderMessage) o;

    return message != null ? message.equals(that.message) : that.message == null;
  }

  @Override
  public int hashCode() {
    return message != null ? message.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "OrderMessage{" +
            "message='" + message + '\'' +
            '}';
  }
}
