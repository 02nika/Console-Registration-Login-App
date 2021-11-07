package Factory;

import Helper.Helper;
import User.User;
import command.allCommandClass;

import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Factory {
    public static User createUserOBJFromTerminal() throws FileNotFoundException {
        // this method listening the input.
        // after that it creates User Class object.

        Scanner scanner = new Scanner(System.in);
        String username, password, input;

        Helper.println("enter username information");
        System.out.print(" > ");
        input = scanner.nextLine();
        username = input;

        if(username.equals("exit"))
            System.exit(0);

        if(Helper.checkIfUserExits(input)) {
            Helper.println("user with this username already exists!");
            allCommandClass.registration();
        }


        Helper.println("enter password information");
        System.out.print(" > ");
        input = scanner.nextLine();
        password = input;

        if(password.equals("exit"))
            System.exit(0);

        return new User(username,password);
    }

    public static User createUserOBJ(String UserName, String Password){
        return new User(UserName, Password);
    }
}
