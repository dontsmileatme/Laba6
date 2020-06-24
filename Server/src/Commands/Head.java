package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

/**
 * Команда, выводящая первый элемент коллекции.
 */
public class Head implements Commandable {
    private final String name = "head";

    public Head() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        if (HumanBeingCollection.getSize() != 0) {
            return "Первый элемент коллекции: \n" + HumanBeingCollection.getCollection().stream()
                    .findFirst().get().getHumanBeing() + "\n";
        } else return "Коллекция пустая." + "\n";
    }
}
