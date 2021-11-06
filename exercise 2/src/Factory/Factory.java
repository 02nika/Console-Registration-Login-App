package Factory;

import Helper.Helper;
import User.User;

import java.util.Scanner;

public final class Factory {
    public static User createUserOBJFromTerminal(){
        Scanner scanner = new Scanner(System.in);
        String username, password, input;

        Helper.println("enter username information");
        System.out.print(" > ");
        input = scanner.nextLine();
        username = input;

        Helper.println("enter password information");
        System.out.print(" > ");
        input = scanner.nextLine();
        password = input;

        return new User(username,password);
    }

    public static User createUserOBJ(String UserName, String Password){
        return new User(UserName, Password);
    }
}
