import java.util.Scanner;

import Client.Client;
import Server.Server;

public class App {
    public static void main(String[] args) {
        System.out.println("Do you want to start: \n(1) Server \n(2) Client ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.close();
        switch (choice) {
            case 1:
                Server.startMain();
                break;
            case 2:
                Client.startMain();
                break;
            default:
                System.err.println("Error: Option not listed");
                break;
        }
    }
}
