package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

/**
 * Команда, выводящая информацию о коллекции.
 */
public class Info implements Commandable {
    private final String name = "info";

    public Info() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        return HumanBeingCollection.getInfo() + "\n";
    }
}
