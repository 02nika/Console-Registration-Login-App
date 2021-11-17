package Factory;

import Helper.Helper;
import User.User;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public final class Factory {

    public static User enterUserOBJParamsFromTerm(){
        Scanner scanner = new Scanner(System.in);


        System.out.print(" > enter user name: ");
        String input = scanner.nextLine();
        String userName = input;


        System.out.print(" > enter password: ");
        input = scanner.nextLine();
        String password = input;

        return  createUserOBJ(userName, password);
    }

    public static User createUserOBJ(String UserName, String Password){
        return new User(UserName, Password);
    }

    public static String userNameParam(List<User> personsList) {
        // this method trying to read input, which is username value.
        Scanner scanner = new Scanner(System.in);

        System.out.print(" > enter Username: ");
        String userN = scanner.nextLine();

        if(userN.toLowerCase(Locale.ROOT).equals("exit")) {
            // goodbye message.
            Helper.println("firewall!");

            System.exit(0);
        }

        for (User person: personsList) {
            if(person.get_userName().equals(userN))
                return userN;
        }
        return null;
    }

    public static String passwordParam(List<User> personsList){
        // this method trying to read input, which is username value.
        Scanner scanner = new Scanner(System.in);

        System.out.print(" > enter Password: ");
        String password = scanner.nextLine();

        for (User person: personsList) {
            if(person.get_password().equals(password))
                return password;
        }
        return null;
    }

    public static String getTextFromTerminal(){
        Scanner scanner = new Scanner(System.in);

        System.out.print(" > ");
        String input = scanner.nextLine();
        String text = input;

        return  text;
    }
}
