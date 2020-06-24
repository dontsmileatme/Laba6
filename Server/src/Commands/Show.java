package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

import java.util.Comparator;

/**
 * Команда, выводящая все элементы коллекции.
 */
public class Show implements Commandable {
    private final String name = "show";
    String result;

    public Show() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        if (!HumanBeingCollection.getCollection().isEmpty()) {
            HumanBeingCollection.getCollection().stream()
                    .sorted(Comparator.comparing(HumanBeing::getName))
                    .forEach(x -> addResult(x.getHumanBeing()));
            String str = getResult();
            result = "";
            return "Элементы коллекции: \n" + str + "\n";
        } else return "Коллекция пустая" + "\n";
    }

    public void addResult(String str) {
        this.result += str;
    }

    public String getResult() {
        return result;
    }
}
