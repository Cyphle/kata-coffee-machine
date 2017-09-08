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

### Step 2
The coffee machine is not free anymore! One tea is 0,4 euro, a coffee is 0,6 euro, a chocolate is 0,5 euro.

- The drink maker should make the drinks only if the correct amount of money is given
    - As CoffeeMachine
    - I want to send order to drink maker only if right amount of money is given
    - In order to not loose money  
- If not enough money is provided, we want to send a message to the drink maker. The message should contains at least the amount of money missing.
    - As CoffeeMachine
    - I want to send message to drink maker that tells that money is missing
    - In order for him to forward it to client
    
### Step 3
- I want to be able to buy a orange juice for 0,6 euro
    - As customer
    - I want to buy an orange juice for 0.6 euro
    - In order to drink one
- I want to be able to have my coffee, chocolate or tea extra hot
    - As customer
    - I want to be able to choose the temperature of my hot beverage
    - In order to have hotter beverage (coffee, tea, chocolate)

#### Drink maker protocol
Drink maker receives string such as:
- "T:1:0" (Drink maker makes 1 tea with 1 sugar and a stick)
- "H::" (Drink maker makes 1 chocolate with no sugar -  and therefore no stick)
- "C:2:0" (Drink maker makes 1 coffee with 2 sugars and a stick)
- "M:message-content" (Drink maker forwards any message received onto the coffee machine interface for the customer to see)
- "O::" (Drink maker will make one orange juice)
- "Ch::" (Drink maker will make an extra hot coffee with no sugar)
- "Hh:1:0" (Drink maker will make an extra hot chocolate with one sugar and a stick)
- "Th:2:0" (The drink maker will make an extra hot tea with two sugar and a stick)

## URL
http://simcap.github.io/coffeemachine/