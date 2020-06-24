package Commands;

import Human.HumanBeing;

/**
 * Команда, выводящая перечень всех команд.
 */
public class Help implements Commandable {
    private final String name = "help";

    public Help() {
        Command.regist(name, this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String arg, HumanBeing human) {
        return "help: вывести справку по доступным командам\n" +
                "info: вывести в стандартный поток вывода информацию о коллекции\n" +
                "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element}: добавить новый элемент в коллекцию\n" +
                "update id {element}: обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id: удалить элемент из коллекции по его id\n" +
                "clear: очистить коллекцию\n" +
                //"save: сохранить коллекцию в файл\n" +
                "execute_script file_name: считать и исполнить скрипт из указанного файла; в скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n" +
                "exit: завершить программу (без сохранения в файл)\n" +
                "head: вывести первый элемент коллекции\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_greater {element}: удалить из коллекции все элементы, превышающие заданный\n" +
                "count_by_impact_speed impactSpeed: вывести количество элементов, значение поля impactSpeed которых меньше заданного\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                "print_field_descending_minutes_of_waiting minutesOfWaiting : вывести значения поля minutesOfWaiting в порядке убывания\n" + "\n";
    }
}
