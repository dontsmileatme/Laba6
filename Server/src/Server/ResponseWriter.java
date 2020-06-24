package Server;

import ServerConnection.Connection;
import Tools.Serializer;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ResponseWriter {
    String response;
    Connection connection;
    SelectionKey key;
    Selector selector;

    public ResponseWriter(String response, Connection connection, SelectionKey key,  Selector selector) {
        this.response = response;
        this.connection = connection;
        this.key = key;
        this.selector = selector;
    }

    public void sendResponse() throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        connection.write(Serializer.toSerialize(response), channel);
        try {
            channel.register(selector, SelectionKey.OP_READ);
            //key.channel().register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }
}
