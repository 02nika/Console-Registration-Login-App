package com.company;

import Helper.Helper;
import command.allCommandClass;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final String fileName = "Users.txt";

    public static void main(String[] args) throws FileNotFoundException {
        // if someone was logged in after shutting down, the system
        // it will log out the user.
        Helper.LogOutEveryOne(fileName);


        // hello message
        System.out.println("   hello this is my brand new terminal. enjoy it. ");

        // terminal loop, waiting for command and enter.
        while (true){
            System.out.print(" > ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // searching the input text.
            mySwitch(input.toLowerCase(Locale.ROOT));

            // after you enter "exit" command terminal loop breaks.
            if (input.equals("exit")) {
                break;
            }
        }

        // goodbye message.
        Helper.println("firewall!");
    }

    public static void mySwitch(String text) throws FileNotFoundException {
        switch (text){
            case "help":
                allCommandClass.help();
                break;
            case "status":
                allCommandClass.status();
                break;
            case "login":
                allCommandClass.login();
                break;
            case "registration":
                Helper.println("Starting Registration...");
                allCommandClass.registration();
                break;
            case "log out":
                allCommandClass.logOutUser();
                break;
            case "exit":
                break;
            default:
                System.out.println("command not found!");
        }
    }

}
