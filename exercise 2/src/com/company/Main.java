package com.company;

import Helper.Helper;
import User.User;
import command.allCommandClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // before programs begins, we need to clear file and create new User list.
        List<User> personsList = new ArrayList<>();
        Helper.clearFile();

        // note that:
        // everything what's going on in the memory, we are saving it also in personsList (List<User>)

        // hello message
        System.out.println("   hello this is my brand new terminal. enjoy it. " +
                "\n   if you want to know all the commands, type help");

        // terminal loop, waiting for command and enter.
        while (true){
            System.out.print(" > ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // searching the input text.
            // user's input will become toLowerCase, so that means that case-sensitive doesn't matter.
            personsList = mySwitch(input.toLowerCase(Locale.ROOT), personsList);

            // after you enter "exit" command terminal loop breaks.
            if (input.equals("exit")) {
                break;
            }
        }

        // goodbye message.
        Helper.println("firewall!");
    }

    public static List<User> mySwitch(String text, List<User> personsList)
            throws IOException, ClassNotFoundException {

        switch (text){
            case "help":
                allCommandClass.help();
                break;
            case "status":
                allCommandClass.status(personsList);
                break;
            case "login":
                personsList = allCommandClass.login(personsList);
                break;
            case "registration":
                Helper.println("Starting Registration...");
                personsList = allCommandClass.registration(personsList);
                break;
            case "log out":
                personsList = allCommandClass.logOutUser(personsList);
                break;
            case "exit":
                break;
            default:
                System.out.println("command not found!");
        }
        return personsList;
    }

}
