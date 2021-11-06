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
        // if someone is logged in after executing login method
        // first we need to log every one out.
        Helper.LogOutEveryOne(fileName);

        // this two lines of code compares
        // input values to username value and password value.
        String usernamePar = userNameParam();
        String passwordPar = passwordParam();

        // if username or password is incorrect
        // it shows the message and executes himself.
        if (usernamePar == null){
            Helper.println("Username is not correct!");
            login();
        }
        else if (passwordPar == null)
        {
            Helper.println("password is not correct!");
            login();
        }

        if (usernamePar != null && passwordPar != null){
            User myUser = Factory.createUserOBJ(usernamePar, passwordPar);
            isLoggedIn(myUser);
            Helper.println("User '" + myUser.get_userName() + "' is logged in!");
        }
    }

    public static void logOutUser() throws FileNotFoundException {
        // inside the list of Strings are all lines of text file.
        List<String> allLines = new ArrayList<>();
        insertAllLinesIntoList(fileName, allLines);

        // in this fragment of code, we remove all extra spaces
        // and the Dot: Which indicates user is logged in.
        String loggedInUser = "";
        int iterator = 0;
        for (String element: allLines) {
            if(element.contains(".")){
                allLines.set(iterator, element.substring(0, element.length()-5));
                loggedInUser = element.split("    ")[0];
            }
            iterator++;
        }

        // after that we're writing all the String lines inside the text file
        Helper.writeLinesIntoFile(fileName, allLines, false);

        // in this fragment of code, we are finally checking, if user is still logged in or not
        if(!loggedInUser.equals(""))
            Helper.println("user: " + loggedInUser + " is logged out!");
    }

    private static void isLoggedIn(User user)
            throws FileNotFoundException {

        // inside the list of Strings are all lines of text file.
        List<String> allLines = new ArrayList<>();
        insertAllLinesIntoList(fileName, allLines);

        // which user trying to logged in, we gave him 4 extra spaces and dot.
        // that means that user saves with: username, password and dot.
        int iterator = 0;
        for (String element: allLines) {
            if(user.toString().equals(element))
                allLines.set(iterator, element + "    .");
            iterator++;
        }

        // after that we're writing all the String lines inside the text file
        Helper.writeLinesIntoFile(fileName, allLines, false);
    }

    private static String userNameParam() throws FileNotFoundException {
        // this method trying to read input, which is username value.
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
        // this method trying to read input, which is username value.
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
