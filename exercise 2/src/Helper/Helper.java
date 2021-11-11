package Helper;

import User.User;

import java.io.*;
import java.util.List;

public final class Helper {
    static final String fileName = "Users.txt";

    public static void println(String text){
        // this println method help us to print every thing after this symbol: >
        System.out.println(" > " + text);
    }

    public static boolean contains(String userName, List<User> personsList) {
        for (User user: personsList) {
            if(userName.equals(user.get_userName()))
                return true;
        }
        return false;
    }

    public static List<User> registerUserHashed(User user, List<User> personsList) {
        user.set_loggedIn(true);
        personsList.add(user);
        return personsList;
    }

    public static List<User> addListOfUsersIntoTheFile(List<User> personsList)
            throws IOException, ClassNotFoundException {

        // კითხულობს ფაილს
        // და აღნიშნულ ფაილში წერს გადაცემულ ობიექტების ლისტს
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(personsList);
        objectOutputStream.close();


        // კითხულობს ფაილს
        // და აღნიშნული ფაილიდან იღებს ობიექტის ლისტს, და კასტის მეშვეობით აბრუნებს მას.
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        List<User> pS2 = (List<User>) objectInputStream.readObject();
        objectInputStream.close();

        //System.out.println(pS2);
        return pS2;
    }

    public static void clearFile(){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
