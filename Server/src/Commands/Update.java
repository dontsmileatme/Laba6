package Commands;

import Collection.HumanBeingCollection;
import Human.HumanBeing;

import java.util.Iterator;

/**
 * Команда, обновляющая элемент коллекции по id.
 */
public class Update implements Commandable {
    private final String name = "update";

    public Update() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        long id = Long.parseLong(arg);
        boolean flag = false;
        HumanBeing humanToRemove = null;
        Iterator<HumanBeing> iterator = HumanBeingCollection.getCollection().iterator();
        while (iterator.hasNext()) {
            HumanBeing h = iterator.next();
            if (h.getId() == id) {
                flag = true;
                humanToRemove = h;
            }
        }

        if (flag) {
            HumanBeingCollection.getCollection().remove(humanToRemove);
            HumanBeing humanToAdd = human;
            humanToAdd.setId(id);
            HumanBeingCollection.getCollection().add(humanToAdd);
            return "Человек с id — " + id + " заменён." + "\n";
        } else return "Человека с id — " + id + " не нашлось." + "\n";
    }
}
