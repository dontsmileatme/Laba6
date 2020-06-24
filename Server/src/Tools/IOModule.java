package Tools;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOModule {
    public static String filepath = System.getenv("JavaTextFile");

    public static String readFromFile(String filepath) {
        try {
            File file = new File(filepath);
            FileReader reader = new FileReader(file);
            String data = "";
            while (reader.ready()) {
                data += (char) reader.read();
            }
            reader.close();
            return data;
        } catch (IOException ex) {
            return null;
        }
    }

    public static boolean writeToFile(String data, String filepath) throws IOException {
        try {
            File file = new File(filepath);
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
