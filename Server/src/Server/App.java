package Server;

import Collection.HumanBeingCollection;
import Commands.*;
import Tools.FillingTheCollection;
import Tools.IOModule;

public class App {
    static final int PORT = 2099;
    public static void main(String[] args) {
        initializeCommands();
        Server server = new Server(PORT);
        HumanBeingCollection humans = new HumanBeingCollection();
        SaveCollection save = new SaveCollection();
        save.checkCollectionFile();
        String filepath = IOModule.filepath;
        FillingTheCollection.fillTheCollection(filepath);
        save.checkForCommand();
        if (server.isConnected()) {
            server.start();
        }
    }

    private static void initializeCommands() {
        Add add = new Add();
        Add_if_max add_if_max = new Add_if_max();
        Clear clear = new Clear();
        Count_by_impact_speed count_by_impact_speed = new Count_by_impact_speed();
        Execute_script execute_script = new Execute_script();
        Exit exit = new Exit();
        Filter_starts_with_name filter_starts_with_name = new Filter_starts_with_name();
        Head head = new Head();
        Help help = new Help();
        Info info = new Info();
        Print_field_descending_minutes_of_waiting print_field_descending_minutes_of_waiting = new Print_field_descending_minutes_of_waiting();
        Remove_by_id remove_by_id = new Remove_by_id();
        Remove_greater remove_greater = new Remove_greater();
        Show show = new Show();
        Update update = new Update();
    }
}
