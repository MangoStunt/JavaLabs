
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;
import java.util.Comparator;

public class ReadFileLineByLine {

    public static void main(String[] args) {
        try {
            File file = new File("testTaskFile.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            ArrayList<String> lines = new ArrayList<>();
            while (line != null) {
                System.out.println(line);
                lines.add(line);
                line = reader.readLine();
            }
            System.out.println("/////////////////////////////////////////////////////////");
//            Collections.sort(lines, Collections.reverseOrder());
            lines.sort(Comparator.comparingInt(String::length));
            for (int i = 0; i < lines.size(); i++) {
            }
            } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
