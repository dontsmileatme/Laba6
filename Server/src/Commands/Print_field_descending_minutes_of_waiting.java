package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

import java.util.Comparator;

/**
 * Команда, выводящая значения поля minutesOfWaiting в порядке убывания.
 */
public class Print_field_descending_minutes_of_waiting implements Commandable {
    private final String name = "print_field_descending_minutes_of_waiting";
    String result;

    public Print_field_descending_minutes_of_waiting() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        HumanBeingCollection.getCollection().stream()
                .sorted(Comparator.comparing(HumanBeing::getMinutesOfWaiting).reversed())
                .forEach(x -> addResult(String.valueOf(x.getMinutesOfWaiting())));
        if (result != null) {
            String str = getResult();
            result = "";
            return "Значения 'minutesOfWaiting' в порядке убывания: \n" + str + "\n";
        } else return "Коллекция пустая." + "\n";
    }

    public void addResult(String str) {
        this.result = result + str + "\n";
    }

    public String getResult() {
        return result;
    }
}


