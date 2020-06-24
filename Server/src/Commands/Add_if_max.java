package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

/**
 * Команда, добавляющая элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.
 */
public class Add_if_max implements Commandable {
    private final String name = "add_if_max";

    public Add_if_max() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        if (!HumanBeingCollection.getCollection().isEmpty()) {
            HumanBeing head = HumanBeingCollection.getCollection().stream().findFirst().get();
            if (human.compareTo(head) > 0) {
                HumanBeingCollection.addHuman(human);
                return "Человек [" + human.getName() + "] добавлен в коллекцию." + "\n";
            } else return "Человек [" + human.getName() + "] не был добавлен в коллекцию." + "\n";
        } else {
            HumanBeingCollection.addHuman(human);
            return "Человек [" + human.getName() + "] добавлен в коллекцию." + "\n";
        }
    }
}
