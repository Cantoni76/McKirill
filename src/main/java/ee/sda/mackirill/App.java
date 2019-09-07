package ee.sda.mackirill;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MacKirill restaurant system");
        System.out.println("Print commanfdsfgd. 1 Booking, extit");

        switch (scanner.nextLine().toLowerCase()) {
            case "1":
                break;
            case "exit":
                return;
        }
    }
}
