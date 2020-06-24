package Server;

import ServerConnection.Connection;
import Tools.Deserializer;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class RequestReader {
    SelectionKey key;
    Connection connection;
    Selector selector;

    public RequestReader(SelectionKey key, Connection connection, Selector selector) {
        this.key = key;
        this.connection = connection;
        this.selector = selector;
    }

    public String readRequest() throws IOException {
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            byte[] bytes = connection.read(channel);
            channel.register(selector, SelectionKey.OP_WRITE);
            //key.channel().register(selector, SelectionKey.OP_WRITE);
            if (bytes == null) return null;
            int flag = Deserializer.flagCheck(bytes);
            CommandExecutor executor = new CommandExecutor(flag, bytes);
            return executor.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
