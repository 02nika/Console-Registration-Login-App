package command;

import Factory.Factory;
import Helper.Helper;
import User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Factory.Factory.createUserOBJFromTerminal;
import static Helper.Helper.insertAllLinesIntoList;

public final class allCommandClass {

    static final String fileName = "Users.txt";

    public static void help(){
        System.out.println("in this terminal are some commands that you can use: ");

        System.out.println(" - " + "exit");
        System.out.println(" - " + "registration");
        System.out.println(" - " + "login");
    }

    public static void registration() throws FileNotFoundException {
        Helper.LogOutEveryOne(fileName);
        try
        {
            //append into file connection.
            PrintWriter outFileStream = new PrintWriter(new FileOutputStream(fileName, true));

            //create User class.
            User student = createUserOBJFromTerminal();

            //insert User into file and save file.
            outFileStream.println(student);
            outFileStream.close();

            // add dot for registered user,
            // p.s that means that which line has dotted.
            // that line user is logged in.
            isLoggedIn(student);

            Helper.println("User registered successfully!");
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Exception occurred..");
        }

    }

    public static void login() throws FileNotFoundException {
        Helper.LogOutEveryOne(fileName);

        String usernamePar = userNameParam();
        String passwordPar = passwordParam();

        if (usernamePar != null && passwordPar != null){
            User myUser = Factory.createUserOBJ(usernamePar, passwordPar);
            isLoggedIn(myUser);
            Helper.println("User '" + myUser.get_userName() + "' is logged in!");
        }
    }

    public static void logOutUser() throws FileNotFoundException {
        List<String> allLines = new ArrayList<>();
        insertAllLinesIntoList(fileName, allLines);

        String loggedInUser = "";
        int iterator = 0;
        for (String element: allLines) {
            if(element.contains(".")){
                allLines.set(iterator, element.substring(0, element.length()-5));
                loggedInUser = element.split("    ")[0];
            }
            iterator++;
        }

        Helper.writeLinesIntoFile(fileName, allLines, false);
        if(!loggedInUser.equals(""))
            Helper.println("user: " + loggedInUser + " is logged out!");
    }

    private static void isLoggedIn(User user)
            throws FileNotFoundException {

        List<String> allLines = new ArrayList<>();
        insertAllLinesIntoList(fileName, allLines);

        int iterator = 0;
        for (String element: allLines) {
            if(user.toString().equals(element))
                allLines.set(iterator, element + "    .");
            iterator++;
        }

        Helper.writeLinesIntoFile(fileName, allLines, false);
    }

    private static String userNameParam() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<String> allLines = new ArrayList<>();
        Helper.insertAllLinesIntoList(fileName, allLines);

        Helper.println("enter Username:");
        System.out.print(" > ");
        String userN = scanner.nextLine();

        for (String line: allLines) {
            if(line.split("    ")[0].equals(userN))
                return userN;
        }
        return null;
    }

    private static String passwordParam() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<String> allLines = new ArrayList<>();
        Helper.insertAllLinesIntoList(fileName, allLines);

        Helper.println("enter Password:");
        System.out.print(" > ");
        String password = scanner.nextLine();

        for (String line: allLines) {
            if(line.split("    ")[1].equals(password))
                return password;
        }
        return null;
    }

}
