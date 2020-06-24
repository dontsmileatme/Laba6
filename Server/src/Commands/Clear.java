package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

/**
 * Команда, очищающая коллекцию.
 */
public class Clear implements Commandable {
    private final String name = "clear";

    public Clear() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        int size = HumanBeingCollection.getSize();
        if (size > 0) {
            HumanBeingCollection.getCollection().clear();
            return "Количество удалённых элементов из коллекции: " + size + "\n";
        } else return "Коллекция пустая." + "\n";
    }

}
