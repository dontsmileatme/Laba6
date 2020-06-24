package Server;

import Commands.Command;
import Data.CommandShell;
import Data.CommandShellsBundle;
import Tools.Deserializer;

import java.io.IOException;

public class CommandExecutor {
    int flag;
    byte[] bytes;

    public CommandExecutor(int flag, byte[] bytes) {
        this.flag = flag;
        this.bytes = bytes;
    }

    public String execute() throws IOException {
        if (flag == 1) {
            return executeCommand();
        } else if (flag == 2) {
            return executeScript();
        } else return null;
    }

    public String executeCommand() throws IOException {
        CommandShell shell = Deserializer.toDeserialize(bytes, CommandShell.class);
        Command command = new Command(shell.getCommandName(), shell.getCommandArg(), shell.getHuman());
        //System.out.println(command.execute());
        return command.execute();
    }

    public String executeScript() throws IOException {
        String result = "";
        CommandShellsBundle shellsBundle = Deserializer.toDeserialize(bytes, CommandShellsBundle.class);
        for (int i = 0; i < shellsBundle.getSize(); i++) {
            CommandShell shell = shellsBundle.getShell(i);
            Command command = new Command(shell.getCommandName(), shell.getCommandArg(), shell.getHuman());
            result += command.execute();
        }
        //System.out.println(result);
        return result;
    }
}
