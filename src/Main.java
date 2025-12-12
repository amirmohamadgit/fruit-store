import entities.Fruit;
import entities.Seller;
import services.CostumerService;
import services.SellerService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        while (true) {
            System.out.println(
                    """
                            1.login
                            2.register
                            """);
            System.out.print("1 or 2: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
            }
        }
    }

    public static void login() {
        System.out.println(
                """
                        1.admin
                        2.costumer
                        """);
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                System.out.println("name: ");
                String name = scanner.nextLine();
                System.out.println("password: ");
                String password = scanner.nextLine();
                if (SellerService.login(name, password)) sellerMenu();
                break;
            }
            case 2: {
                System.out.println("phone number: ");
                String phoneNumber = scanner.nextLine();
                if (CostumerService.login(phoneNumber)) costumerMenu();
            }
        }

    }

    public static void sellerMenu() {
        outer:
        while (true) {
            System.out.println(
                    """
                            1.add fruit
                            2.edit fruit (inventory or price)
                            3.remove fruit
                            4.all fruits
                            5.exit
                            """);
            System.out.println("enter: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("name: ");
                    String name = scanner.nextLine();
                    System.out.println("description: ");
                    String description = scanner.nextLine();
                    System.out.println("inventory");
                    int inventory = Integer.parseInt(scanner.nextLine());
                    System.out.println("price: ");
                    long price = Long.parseLong(scanner.nextLine());
                    SellerService.addFruit(new Fruit(name,description,inventory,price));
                    break;
                }
                case 2: {}
                case 3: {}
                case 4: {}
                case 5: break outer;
            }
        }
    }

    public static void costumerMenu() {
        System.out.println("all fruits: ");
        List<String> fruits = CostumerService.allFruits();
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }

    public static void register(){}
}
