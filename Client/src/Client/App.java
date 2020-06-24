package Client;

import java.io.IOException;

/***
 * @version 1.0
 * @author Andrew
 */

public class App {
    static final int PORT = 2099;

    public static void main(String[] args) throws IOException {
        Client client = new Client(PORT);
    }
}
