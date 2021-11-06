package Helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Helper {

    public static void writeLinesIntoFile(String Path, List<String> allLines, boolean append)
            throws FileNotFoundException {

        PrintWriter outFileStream = new PrintWriter(new FileOutputStream(Path, append));

        for (String singleLine : allLines) {
            outFileStream.println(singleLine);
        }
        outFileStream.close();
    }

    public static List<String> insertAllLinesIntoList(String path, List<String> mainList){
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
        List<String> allLines = new ArrayList<>();
        insertAllLinesIntoList(path, allLines);

        int iterator = 0;
        for (String element: allLines) {
            String[] arr = element.split("    ");
            allLines.set(iterator, arr[0] + "    " + arr[1]);
            iterator++;
        }

        writeLinesIntoFile(path, allLines, false);
    }

    public static void println(String text){
        System.out.println(" > " + text);
    }

}
