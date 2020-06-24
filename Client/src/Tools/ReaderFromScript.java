package Tools;

import Controller.Controller;
import Data.CommandShellsCollection;

import java.io.*;

import static Tools.FileCheck.checkFile;

public class ReaderFromScript {
    public static int linesCounting = 0;
    Controller controller = new Controller();

    public void readFromScript(String filename) throws IOException {
        try {
            File file = new File(filename);
            if (checkFile(filename) == 0) {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str = "";
                CommandShellsCollection collection = new CommandShellsCollection();
                while (bufferedReader.ready()) {
                    str = bufferedReader.readLine().replaceAll("\\s+", " ");
                    if (str.isEmpty()) continue;
                    linesCounting++;
                    if (controller.isCommandCorrect(str)) {
                        collection.addShell(controller.getShell());
                        String[] temp = str.split(" ");
                        if (temp[0].equals("add") || temp[0].equals("add_if_max") || temp[0].equals("remove_greater") || temp[0].equals("update")) {
                            for (int i = 0; i < 11; i++) {
                                bufferedReader.readLine();
                            }
                        }
                    }
                }
                //bufferedReader.close();
                //reader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Неверное имя файла.");
        }
    }
}


