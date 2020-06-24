package Server;

import Collection.HumanBeingCollection;
import Commands.Command;
import Tools.FileCheck;
import Tools.IOModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SaveCollection {
    public void checkForCommand() {
        Thread backgroundReaderThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!Thread.interrupted()) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.equalsIgnoreCase("save")) {
                        System.out.println("Идёт сохранение...");
                        saveCollection();
                        System.out.println("Коллекция сохранена в файл.");
                    }
                    if (line.equalsIgnoreCase("exit")) {
                        Command command = new Command("exit", null, null);
                        command.execute();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }

    public void saveCollection() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = gson.toJson(HumanBeingCollection.getCollection());
        IOModule.writeToFile(data, IOModule.filepath);
    }

    public void checkCollectionFile() {
        try {
            if (!(IOModule.filepath == null)) {
                if (FileCheck.checkFile(IOModule.filepath) == -1) {
                    throw new IllegalArgumentException();
                } else System.out.println("Файл для работы с коллекцией найден.");
            } else throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.out.println("Необходимо указать верное имя существующего JSON-файла: ");
            IOModule.filepath = new Scanner(System.in).nextLine();
            this.checkCollectionFile();
        }
    }
}

