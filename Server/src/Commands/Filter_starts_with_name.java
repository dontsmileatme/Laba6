package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

import java.util.Comparator;

/**
 * Команда, выводящая элементы, значение поля name которых начинается с заданной подстроки.
 */
public class Filter_starts_with_name implements Commandable {
    private final String name = "filter_starts_with_name";
    String result = "";

    public Filter_starts_with_name() {
        Command.regist("filter_starts_with_name", this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        HumanBeingCollection.getCollection().stream()
                .filter(p -> p.getName().startsWith(arg))
                .sorted(Comparator.comparing(HumanBeing::getName))
                .forEach(x -> addResult(x.getHumanBeing()));
        if (result != null) {
            String str = getResult();
            result = "";
            return "Люди, имя которых начинается с подстроки '" + arg + "':\n" + str + "\n";
        } else return "Людей, имя которых начинается с подстроки '" + arg + "' не нашлось." + "\n";
    }

    public void addResult(String str) {
        this.result += str;
    }

    public String getResult() {
        return result;
    }
}
