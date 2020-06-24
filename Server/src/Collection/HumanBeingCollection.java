package Collection;

import Human.HumanBeing;

import java.io.Serializable;
import java.util.Date;
import java.util.PriorityQueue;

/**
 * Reciever-класс, содержащий коллекцию.
 */
public class HumanBeingCollection implements Serializable {
    protected static PriorityQueue<HumanBeing> humans = new PriorityQueue<>();
    protected static Date dateCreation;

    public HumanBeingCollection() {
        dateCreation = new Date();
        System.out.println("Рабочая коллекция создана.");
    }

    public static PriorityQueue<HumanBeing> getCollection() {
        return humans;
    }

    public static void setCollection(PriorityQueue<HumanBeing> collection) {
        humans = collection;
    }

    public static void addHuman(HumanBeing human) {
        humans.add(human);
    }

    public static int getSize() {
        return humans.size();
    }

    public static Date getDateCreation() {
        return dateCreation;
    }

    public static String getInfo() {
        return "Тип коллекции: 'Priority Queue'" +
                ";\nKоличество элементов коллекции: " + getSize() +
                ";\nДата создания коллекции: " + getDateCreation() + ".";
    }
}