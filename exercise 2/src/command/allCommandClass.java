package command;

import Factory.Factory;
import Helper.Helper;
import User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Factory.Factory.*;

public final class allCommandClass {

    public static void help(){
        System.out.println("in this terminal are some commands that you can use: ");

        System.out.println(" - " + "help");
        System.out.println(" - " + "status");
        System.out.println(" - " + "exit");
        System.out.println(" - " + "registration");
        System.out.println(" - " + "login");
        System.out.println(" - " + "log out");
    }

    public static List<User> registration(List<User> personsList) throws IOException, ClassNotFoundException {
        personsList = logOutUser(personsList);
        User user = enterUserOBJParamsFromTerm();

        if(user.get_userName().equals("exit") || user.get_password().equals("exit")){
            Helper.println("process stopped");
            return personsList;
        }

        if(Helper.contains(user.get_userName(), personsList)){
            Helper.println("username already exist!");
            registration(personsList);
        }

        Helper.println("user: " + user.get_userName() + " is registered successfully!");
        personsList = Helper.registerUserHashed(user, personsList);

        Helper.addListOfUsersIntoTheFile(personsList);

        Helper.println("Do you want to add another user?");
        String answer = Factory.getTextFromTerminal();

        if(answer.equals("yes"))
            return registration(personsList);
        else{
            Helper.println("type list and see registered users.");
            String text = Factory.getTextFromTerminal();
            if(text.equals("list"))
                System.out.println(personsList);
        }

        return personsList;
    }

    public static List<User> login(List<User> personsList)
            throws IOException, ClassNotFoundException {

        // if someone is logged in after executing login method
        // first we need to log everyone out.
        personsList = logOutUser(personsList);

        // this two lines of code compares
        // input values to username value and password value.
        String usernamePar = userNameParam(personsList);
        String passwordPar = passwordParam(personsList);

        // if username or password is incorrect
        // it shows the message and executes himself.
        if (usernamePar == null || passwordPar == null){
            Helper.println("Username or Password is not correct!");
            login(personsList);
        }

        // if "if statement" will be passed
        // we are changing specific user's status and
        // print into the console.
        if (usernamePar != null && passwordPar != null){
            User myUser = Factory.createUserOBJ(usernamePar, passwordPar);
            personsList = isLoggedIn(myUser, personsList);

            Helper.addListOfUsersIntoTheFile(personsList);
            Helper.println("User '" + myUser.get_userName() + "' is logged in!");
        }
        return personsList;
    }

    public static List<User> logOutUser(List<User> personsList)
            throws IOException, ClassNotFoundException {

        // in here we are taking all the old Users,
        // but without logged in status.
        List<User> newListOfPersons = new ArrayList<>();

        for (User person: personsList) {
            if(person.is_loggedIn()) {
                person.set_loggedIn(false);
                Helper.println(person.get_userName() + " is logged out.");
            }
            newListOfPersons.add(person);
        }

        // updating file.
        Helper.addListOfUsersIntoTheFile(newListOfPersons);

        return newListOfPersons;
    }

    public static void status(List<User> personsList){

        // status tell's us if someone is logged in or not.
        int flag = 0;
        for (User person: personsList) {
            if(person.is_loggedIn()){
                Helper.println(person.get_userName() + " is logged in!");
                flag = 1;
            }
        }

        if (flag == 0)
            Helper.println("there is no one logged in!");
    }

    private static List<User> isLoggedIn(User user, List<User> personsList) {

        // if users password and username is equal to persons list somebodies username and password
        // we are setting logged in status to true.
        for (User element: personsList) {
            if((user.get_userName().equals(element.get_userName())) && (user.get_password().equals(element.get_password())))
                element.set_loggedIn(true);
        }

        return personsList;
    }


}
