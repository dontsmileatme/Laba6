package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

/**
 * Команда, удаляющая все элементы, превышающие заданный.
 */
public class Remove_greater implements Commandable {
    private final String name = "remove_greater";

    public Remove_greater() {
        Command.regist("remove_greater", this);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        int size1 = HumanBeingCollection.getSize();
        HumanBeingCollection.getCollection().removeIf(p -> (p.compareTo(human) > 0));
        int size2 = HumanBeingCollection.getSize();
        return "Количество удалённых человек: " + (size1 - size2) + "." + "\n";
    }
}
