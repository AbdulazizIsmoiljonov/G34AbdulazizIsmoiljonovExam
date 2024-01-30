package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.example.service.EmailService;
import org.example.service.UserService;
import org.example.service.impl.EmailServiceImpl;
import org.example.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AccountApp {
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserServiceImpl();
    static Scanner scannerInt = new Scanner(System.in);
    static EmailService emailService = new EmailServiceImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    1.Login
                    2.Register
                    0.Exit
                    """);
            System.out.print("Enter ...");
            int choice = scannerInt.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your password : ");
                    String password = scannerStr.nextLine();
                    System.out.print("Enter your email : ");
                    String email = scannerStr.nextLine();
                    System.out.println("We sent verification code to " + email);
                    System.out.print("Enter code :");
                    String code = scannerStr.nextLine();
                    emailService.sendEmail(email,code);
                    userService.login(email, password);
                    userMenu();
                }
                case 2 -> {
                    System.out.print("Enter your full name : ");
                    String fullName = scannerStr.nextLine();
                    System.out.print("Enter your email : ");
                    String email = scannerStr.nextLine();
                    System.out.print("Enter your password : ");
                    String password = scannerStr.nextLine();
                    System.out.println("We sent verification code to " + email);
                    System.out.print("Enter code :");
                    String code = scannerStr.nextLine();
                    emailService.sendEmail(email,code);
                    userService.register(fullName, email, password);
                    userMenu();
                }
            }
        }
    }

    public static void userMenu() {
        System.out.println("""
                1.Change password
                2.About date
                0.Exit
                """);
        System.out.print("Enter...");
        int userChoice = scannerInt.nextInt();
        if (userChoice == 0) {
            return;
        }
        switch (userChoice) {
            case 1 -> {
                System.out.print("Enter your email :");
                String email = scannerStr.nextLine();
                System.out.println("We sent 6 digit verification code to " + email);
                System.out.print("Enter code :");
                String code = scannerStr.nextLine();
                emailService.sendEmail(email,code);
                System.out.print("Enter new password");
                String newPassword = scannerStr.nextLine();
                userService.changePassword(newPassword);
            }
            case 2 -> {
                System.out.print("Enter the month: ");
                int month = scannerInt.nextInt();

                System.out.print("Enter the day: ");
                int day = scannerInt.nextInt();

                scannerInt.close();

                HttpClient httpClient = HttpClients.createDefault();
                String apiUrl = String.format("https://numbersapi.p.rapidapi.com/%d/%d/date?fragment=true&json=true", month, day);

                try {
                    HttpGet request = new HttpGet(apiUrl);
                    request.addHeader("X-RapidAPI-Key", "ca417f602amshf966faf56ed15d3p104dc9jsncb9e2dd56b83");
                    request.addHeader("X-RapidAPI-Host", "numbersapi.p.rapidapi.com");

                    HttpResponse response = httpClient.execute(request);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String line;
                    StringBuilder result = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    System.out.println("API Response: " + result.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
