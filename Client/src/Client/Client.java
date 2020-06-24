package Client;

import ClientConnection.Connection;
import Controller.Controller;
import Controller.Validation;
import Data.CommandShell;
import Data.CommandShellsBundle;
import Data.CommandShellsCollection;
import Tools.Deserializer;
import Tools.Serializer;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client implements Runnable{
    private static Socket socket;
    Connection connection = new Connection();
    private static final int maxAttempts = 3;
    private static final int msToReconnect = 3000;
    private static int attempts = 0;
    static final int PORT = 2099;

    @Override
    public void run() {
        while (true) {
            try (Socket s = new Socket("localhost", PORT)) {
                socket = s;
                System.out.println("Подключение установлено.");
                while (this.isConnected()) {
                    this.begin(socket);
                }
            } catch (IOException e) {
                tryToConnect();
            }
        }
    }

    public void tryToConnect() {
        System.err.println("Ошибка подключения к серверу.");
        if (++attempts >= maxAttempts) {
            System.err.println("Превышено количество попыток подключения. Всем пока. :)");
            System.exit(0);
        } else {
            System.err.println("Повторое подключение будет совершено через " + msToReconnect / 1000 + " секунды.");
            try {
                Thread.sleep(msToReconnect);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return (!socket.isClosed() && socket.isConnected());
    }

    public void begin(Socket socket) throws IOException {
        try {
            while (true) {
                System.out.println("Введите команду: ");
                System.out.print("$ ");
                Scanner scanner = new Scanner(System.in);
                String commandName = scanner.nextLine();
                if (!commandName.equals("")) {
                    Controller controller = new Controller();
                    if (controller.isCommandCorrect(commandName)) {
                        if (!Validation.getIsScript()) {
                            if (send(controller, connection, socket)) break;
                        } else {
                            if (sendScript(connection, socket)) break;
                        }
                        if (receive(connection, socket)) break;
                    } else begin(socket);
                }
            }
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
    }


    private boolean send(Controller controller, Connection connection, Socket socket) throws IOException {
        CommandShell shell = controller.getShell();
        if (Validation.isReadyForSend) {
            connection.write(Serializer.toSerialize(shell), socket);
            if (Connection.isConnected) System.out.println("Команда отправлена на сервер.");
            else return true;
        }
        return false;
    }

    private boolean sendScript(Connection connection, Socket socket) throws IOException {
        CommandShellsCollection shells = new CommandShellsCollection();
        CommandShellsBundle commandsBundle = new CommandShellsBundle();
        commandsBundle.setShellCollection(shells.getShellsCollection());
        if (Validation.isReadyForSend) {
            connection.write(Serializer.toSerialize(commandsBundle), socket);
            if (Connection.isConnected) {
                if (!(commandsBundle.getSize() == 0)) {
                    System.out.println("Команды, отправленные на сервер: ");
                    for (int i = 0; i < shells.getSize(); i++) {
                        System.out.println(shells.getShell(i).getCommandName());
                    }
                } else {
                    System.out.println("Скрипт пустой.");
                    return true;
                }
            } else return true;
        }
        Validation.setIsScript(false);
        shells.clearShellsCollection();
        return false;
    }

    private boolean receive(Connection connection, Socket socket) {
        byte[] inputtedBytes;
        inputtedBytes = connection.read(socket);
        if (inputtedBytes == null) return true;
        String answer = Deserializer.toDeserialize(inputtedBytes, String.class);
        if (answer != null) {
            System.out.println("Ответ от сервера: ");
            System.out.println(answer);
        } else return true;
        return false;
    }
}
