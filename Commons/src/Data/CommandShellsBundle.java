package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandShellsBundle  implements Serializable {
    private static final long serialVersionUID = -2685142447503762062L;
    private List<CommandShell> bundle = new ArrayList<>();

    public void addShell(CommandShell shell) {
        bundle.add(shell);
    }

    public void clearBundle() {
        bundle.clear();
    }

    public CommandShell getShell(int i) {
        return bundle.get(i);
    }

    public int getSize() {
        return bundle.size();
    }

    public void setShellCollection(List<CommandShell> collection){
        bundle = collection;
    }
}
