# Kata Coffee Machine

## Notice

### General
You do not have to implement the coffee machine customer interface. For instance, your code could consume a simple POJO that would represent an order from a customer.

You do not have to implement the drink maker. It is only a imaginery engine that will receive messages according to the protocol. Your job is to build those messages.

You can represent the incoming order of the customer as you wish. For instance, it could be a simple POJO that contains the order details, or a simple String, try to think of the simplest thing that do the job.

### Step 1
The coffee machine can serves 3 type of drinks: tea, coffee, chocolate.

- The drink maker should receive the correct instructions for my coffee / tea / chocolate order.
    - As the drink machine
    - I want to send the right type of drink
    - In order to let the drink maker know what drink to prepare
- I want to be able to send instructions to the drink maker to add one or two sugars
    - As a customer
    - I want to be able to add sugar (one or two or none)
    - In order to drink it sweeter
- When my order contains sugar the drink maker should add a stick (touillette) with it
    - As drink maker
    - I want to be able to add a stick when there is sugar
    - In order for the customer to shake the drink

#### Drink maker protocol
Drink maker receives string such as:
- "T:1:0" (Drink maker makes 1 tea with 1 sugar and a stick)
- "H::" (Drink maker makes 1 chocolate with no sugar -  and therefore no stick)
- "C:2:0" (Drink maker makes 1 coffee with 2 sugars and a stick)
- "M:message-content" (Drink maker forwards any message received onto the coffee machine interface for the customer to see)

## URL
http://simcap.github.io/coffeemachine/