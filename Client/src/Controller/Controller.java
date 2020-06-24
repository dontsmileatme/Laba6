package Controller;

import Data.CommandShell;
import Human.HumanBeing;

import java.io.IOException;

public class Controller {
    private String commandName;
    private String commandArg;
    private HumanBeing human;
    private CommandShell shell;

    public boolean isCommandCorrect(String inputtedCommand) throws IOException {
        String[] commandParts = inputtedCommand.split(" ");
        if (commandParts.length <= 2) {
            Validation validation = new Validation();
            validation.setCommandName(commandParts[0]);
            if (commandParts.length == 2) validation.setCommandArg(commandParts[1]);
            if (validation.check()) {
                this.commandName = validation.getCommandName();
                this.commandArg = validation.getCommandArg();
                this.human = validation.getHumanBeing();
                shell = new CommandShell(commandName, commandArg, human);
                return true;
            } else return false;
        } else {
            System.out.println("Некорректный ввод команды. Введите \"help\", чтобы ознакомиться со всем перечнем команд.");
            return false;
        }
    }

    public CommandShell getShell() {
        return shell;
    }
}
