package ServerConnection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;

public class Connection {
    public void write(byte[] bytes, SocketChannel socketChannel) {
        try {
            socketChannel.write(ByteBuffer.wrap(bytes));
        } catch (Exception e) {
            try {
                socketChannel.close();
            } catch (IOException ex) {
                System.err.println("Соединение утеряно.");
            }
            System.err.println("Ошибка во время записи ответа.");
        }
    }

    public byte[] read(SocketChannel socketChannel) throws IOException {
        int readBytes = 0;
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        try {
            readBytes = socketChannel.read(buffer);
        } catch (IOException | NotYetConnectedException | NonReadableChannelException e) {
            try {
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Соединение утеряно.");
            return null;
        }

        if (readBytes == -1) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.err.println("Достигнут конец stream'а: " + socketChannel.getRemoteAddress());
        }

        byte[] bytes = new byte[100000];
        buffer.rewind();
        buffer.get(bytes, 0, readBytes);
        buffer.clear();

        return bytes;
    }
}
