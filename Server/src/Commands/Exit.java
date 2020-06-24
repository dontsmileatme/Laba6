package Commands;

import Human.HumanBeing;

/**
 * Команда, совершающая выход из программы.
 */
public class Exit implements Commandable {
    private final String name = "exit";

    public Exit() {
        Command.regist(name, this);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        System.exit(0);
        return "Произведён выход из программы.";
    }
}
