package Client;

import java.io.IOException;

/***
 * @version 1.0
 * @author Andrew
 */

public class App {
    private static Thread clientThread;

    public static void main(String[] args)  {
        clientThread = new Thread(new Client());
        clientThread.start();
    }
}
