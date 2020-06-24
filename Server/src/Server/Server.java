package Server;

import ServerConnection.Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    private ServerSocketChannel channel;
    private ServerSocket serverSocket;
    static Selector selector;
    private SelectionKey key;
    Connection connection = new Connection();
    private String response = null;

    public Server(int port) {
        try {
            channel = ServerSocketChannel.open();
            serverSocket = channel.socket();
            selector = Selector.open();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Сервер запущен.");
        } catch (IOException e) {
            System.err.println("Соединение разорвано.");
        }
    }

    public boolean isConnected() {
        return (channel.isOpen() && selector.isOpen());
    }

    public void start() {
        try {
            while (true) {
                selector.select();
                Iterator keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    key = (SelectionKey) keys.next();

                    if (key.isAcceptable()) {
                        SocketChannel client = channel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        RequestReader requestReader = new RequestReader(key, connection, selector);
                        response = requestReader.readRequest();
                        if (response == null) {
                            keys.remove();
                            continue;
                        }
                    }
                    if (key.isWritable()) {
                        ResponseWriter responseWriter = new ResponseWriter(response, connection, key, selector);
                        responseWriter.sendResponse();
                        response = null;
                    }
                    keys.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
