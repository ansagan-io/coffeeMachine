package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int amountOfWater;
    private int amountOfMilk;
    private int amountOfCoffee;
    private int amountOfMoney;
    private int amountOfDispCups;

    public CoffeeMachine(int a, int b, int c, int d, int e) {
        this.amountOfWater = a;
        this.amountOfMilk = b;
        this.amountOfCoffee = c;
        this.amountOfMoney = d;
        this.amountOfDispCups = e;
    }

    enum CoffeeTypes {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        int waterNeed;
        int milkNeed;
        int coffeeNeed;
        int cost;

        CoffeeTypes(int a, int b, int c, int d) {
            this.waterNeed = a;
            this.milkNeed = b;
            this.coffeeNeed = c;
            this.cost = d;
        }
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 550, 9);
        coffeeMachine.mainMenu();
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();
        switch (action) {
            case "buy": this.buy();
                break;
            case "fill": this.fill();
                break;
            case "take": this.takeMoney();
                break;
            case "remaining": this.printState();
                break;
            case "exit": System.exit(0);
                break;
            default:
                System.out.println("Wrong option. Please try again...");
                System.out.println();
                this.mainMenu();
        }
    }

    private void buy() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String action = scanner.next();
        this.buyCoffee(action);
    }

    private void buyCoffee(String coffee) {
        switch (coffee) {
            case "1":
                this.buyCoffee(CoffeeTypes.ESPRESSO);
                System.out.println();
                this.mainMenu();
                break;
            case "2":
                this.buyCoffee(CoffeeTypes.LATTE);
                System.out.println();
                this.mainMenu();
                break;
            case "3":
                this.buyCoffee(CoffeeTypes.CAPPUCCINO);
                System.out.println();
                this.mainMenu();
                break;
            case "back": this.mainMenu();
                break;
            default:
                System.out.println("Wrong choice. Please try again...");
                System.out.println();
                this.buy();
        }
    }

    public void buyCoffee (CoffeeTypes coffee) {
        if (this.amountOfWater < coffee.waterNeed) System.out.println("Sorry, not enough water!");
        if (this.amountOfMilk < coffee.milkNeed) System.out.println("Sorry, not enough milk!");
        if (this.amountOfCoffee < coffee.coffeeNeed) System.out.println("Sorry, not enough coffee!");
        if (this.amountOfCoffee < 1) System.out.println("Sorry, not enough cup!");
        if (this.amountOfWater < coffee.waterNeed||this.amountOfMilk < coffee.milkNeed||this.amountOfCoffee < coffee.coffeeNeed) {
            System.out.println();
            this.mainMenu();
        }
        else {
            System.out.println("I have enough resources, making you a coffee!");
            this.amountOfWater -= coffee.waterNeed;
            this.amountOfMilk -= coffee.milkNeed;
            this.amountOfCoffee -= coffee.coffeeNeed;
            this.amountOfDispCups--;
            this.amountOfMoney = amountOfMoney + coffee.cost;
        }
    }

    private void fill() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        this.amountOfWater += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        this.amountOfMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.amountOfCoffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.amountOfDispCups += scanner.nextInt();
        System.out.println();
        this.mainMenu();
    }

    private void takeMoney() {
        System.out.println();
        System.out.println("I gave you $" + this.amountOfMoney);
        this.amountOfMoney = 0;
        System.out.println();
        this.mainMenu();
    }

    private void printState() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(this.amountOfWater + " of water");
        System.out.println(this.amountOfMilk + " of milk");
        System.out.println(this.amountOfCoffee + " of coffee beans");
        System.out.println(this.amountOfDispCups + " of disposable cups");
        System.out.println(this.amountOfMoney + " of money");
        System.out.println();
        this.mainMenu();
    }
}