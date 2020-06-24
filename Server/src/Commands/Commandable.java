package Commands;

import Human.HumanBeing;

import java.io.IOException;

/**
 * Интерфейс-маркер для всех команд.
 */
public interface Commandable {
    String getName();
    String execute(String arg, HumanBeing human) throws IOException;
}
