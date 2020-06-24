package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandShellsCollection implements Serializable {
    private static final long serialVersionUID = 2964290603384456201L;
    private static List<CommandShell> ShellCollection = new ArrayList<>();

    public void addShell(CommandShell shell) {
        ShellCollection.add(shell);
    }

    public List<CommandShell> getShellsCollection() {
        return ShellCollection;
    }

    public void clearShellsCollection() {
        ShellCollection.clear();
    }

    public CommandShell getShell(int i) {
        return ShellCollection.get(i);
    }

    public int getSize() {
        return ShellCollection.size();
    }
}
