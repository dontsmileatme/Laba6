package Data;

import Human.HumanBeing;

import java.io.Serializable;

public class CommandShell implements Serializable {
    private static final long serialVersionUID = -2725621040143832205L;
    private String commandName;
    private String commandArg;
    private HumanBeing human;

    public CommandShell(String name, String arg, HumanBeing human) {
        this.commandName = name;
        this.commandArg = arg;
        this.human = human;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandArg() {
        return commandArg;
    }

    public HumanBeing getHuman() {
        return human;
    }
}
