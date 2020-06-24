package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

/**
 * Команда, выводящая количество элементов, значение поля impactSpeed которых меньше заданного.
 */
public class Count_by_impact_speed implements Commandable {
    private final String name = "count_by_impact_speed";

    public Count_by_impact_speed() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        long count = HumanBeingCollection.getCollection().stream()
                .filter(p -> p.getImpactSpeed() == Long.parseLong(arg))
                .count();
        if (count != 0) return "Количество элементов со скоростью, равной " + arg + ": " + count + "\n";
        else return "Таких значений не нашлось." + "\n";
    }
}

