package Helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Helper {

    public static void writeLinesIntoFile(String Path, List<String> allLines, boolean append)
            throws FileNotFoundException {

        // this gives connection for the file that we want.
        PrintWriter outFileStream = new PrintWriter(new FileOutputStream(Path, append));

        // after that we are looping to put all the lines inside the file.
        for (String singleLine : allLines) {
            outFileStream.println(singleLine);
        }
        outFileStream.close();
    }

    public static List<String> insertAllLinesIntoList(String path, List<String> mainList){

        // in this try block we are trying to connect to file
        // and after that add all the lines inside the list of strings.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                mainList.add(line);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("My exception");
        }
        return  mainList;
    }

    public static void LogOutEveryOne(String path) throws FileNotFoundException {
        // first, we are trying to put all the lines in the list of string.
        List<String> allLines = new ArrayList<>();
        insertAllLinesIntoList(path, allLines);

        // after that we are parsing the lines of strings.
        // we're only keeping first and second word.
        int iterator = 0;
        for (String element: allLines) {
            String[] arr = element.split("    ");
            allLines.set(iterator, arr[0] + "    " + arr[1]);
            iterator++;
        }

        // after parse the file we are putting this lines
        // inside the file.
        writeLinesIntoFile(path, allLines, false);
    }

    public static void println(String text){
        // this println method help us to print every thing after this symbol: >
        System.out.println(" > " + text);
    }

    public static Boolean checkIfUserExits(String username){
        List<String> allLines = new ArrayList();
        insertAllLinesIntoList("Users.txt", allLines);

        for (String element: allLines) {
            String[] arr = element.split("    ");
            if(arr[0].equals(username))
                return true;
        }

        return false;
    }

}
