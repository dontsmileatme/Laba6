package Controller;

import Human.HumanBeing;
import Tools.HumanCreation;
import Tools.ReaderFromScript;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validation {
    private String commandName;
    private String commandArg;
    private HumanBeing humanBeing;
    private static boolean isScript = false;
    public static boolean isReadyForSend = true;
    private static String filename;
    private static List<String> filenames = new ArrayList<>();

    HumanCreation creation = new HumanCreation();
    ReaderFromScript reader = new ReaderFromScript();

    public boolean check() throws IOException {
        switch (commandName) {
            case "help":
            case "info":
            case "show":
            case "clear":
            case "head":
            case "print_field_descending_minutes_of_waiting":
                return noArgumentCheck();
            case "exit":
                System.out.println("Завершение работы.");
                System.exit(0);
            case "count_by_impact_speed":
                this.speedCheck();
                return true;
            case "execute_script":
                this.scriptCheck();
                return true;
            case "filter_starts_with_name":
                this.nameCheck();
                return true;
            case "remove_by_id":
                this.idCheck();
                return true;
            case "update":
                this.idCheck();
                this.addHuman();
                return true;
            case "add":
            case "add_if_max":
            case "remove_greater":
                this.addHuman();
                return true;

            default:
                System.out.println("Вы ввели некорректную команду.");
                isReadyForSend = false;
                commandName = null;
                return false;
        }
    }

    private boolean noArgumentCheck() {
        if (commandArg == null) {
            isReadyForSend = true;
            return true;
        } else {
            System.out.println("Вы ввели некорректную команду.");
            isReadyForSend = false;
            return false;
        }
    }

    private void idCheck() {
        if (commandArg == null) {
            System.out.println("Введите значение 'id': ");
            System.out.print("$ ");
            String key = new Scanner(System.in).nextLine();
            if (key.equals("")) {
                System.out.println("Значение не должно быть 'null'.");
                this.idCheck();
            } else {
                this.commandArg = key;
                isReadyForSend = true;
            }
        }
    }

    private void scriptCheck() throws IOException {
        filename = commandArg;
        if (filename == null || filename.equals("")) {
            System.out.println("Введите имя файла: ");
            System.out.print("$ ");
            String inputtedFilename = new Scanner(System.in).nextLine();
            if (inputtedFilename.isEmpty()) {
                System.out.println("Имя файла не должно быть 'null'.");
                this.scriptCheck();
            }
        } else {
            if (filenames.contains(filename)) {
                System.err.println("Скрипт уже запущен.");
            } else {
                isScript = true;
                isReadyForSend = true;
                filenames.add(filename);
                reader.readFromScript(filename);
            }
        }
    }

    private void speedCheck() {
        if (commandArg == null) {
            System.out.println("Введите значение 'impact speed': ");
            System.out.print("$ ");
            String key = new Scanner(System.in).nextLine();
            if (key.equals("")) {
                System.out.println("Значение не должно быть 'null'.");
                this.speedCheck();
            } else {
                this.commandArg = key;
                isReadyForSend = true;
            }
        }
    }

    private void nameCheck() {
        if (commandArg == null) {
            System.out.println("Введите значение часть 'name': ");
            System.out.print("$ ");
            String key = new Scanner(System.in).nextLine();
            if (key.equals("")) {
                System.out.println("Значение не должно быть 'null'.");
                this.nameCheck();
            } else {
                this.commandArg = key;
                isReadyForSend = true;
            }
        }
    }

    public void addHuman() throws IOException {
        if (isScript) {
            int count = ReaderFromScript.linesCounting;
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (count-- > 0) bufferedReader.readLine();
            String[] params = new String[11];
            for (int i = 0; i < 11; i++) {
                params[i] = bufferedReader.readLine();
            }
            humanBeing = creation.createFromFile(params);
        } else humanBeing = creation.create();
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArg() {
        return commandArg;
    }

    public HumanBeing getHumanBeing() {
        return humanBeing;
    }

    public static boolean getIsScript() {
        return isScript;
    }

    public void setHumanBeing(HumanBeing humanBeing) {
        this.humanBeing = humanBeing;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandArg(String commandArg) {
        this.commandArg = commandArg;
    }

    public static void setIsScript(boolean is) {
        isScript = is;
    }
}

