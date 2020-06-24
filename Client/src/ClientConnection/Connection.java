package ClientConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class Connection {
    public static boolean isConnected = true;

    public void write(byte[] bytes, Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
        } catch (SocketException e) {
            System.err.println("Соеденение с сервером потеряно.\nКоманда не передана.\n");
            isConnected = false;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Ошибка записи.");
        }
    }

    public byte[] read(Socket socket) {
        int readBytes = 0;
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        try {
            InputStream inputStream = socket.getInputStream();
            readBytes = inputStream.read(buffer.array());
        } catch (SocketException e) {
            System.err.println("Соеденение с сервером потеряно.");
            return null;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Ошибка чтения.");
        }

        if (readBytes == -1) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.err.println("Достигнут конец 'inputStream': " + socket.getInetAddress().getHostAddress());
            return null;
        }

        byte[] bytes = new byte[100000];
        buffer.rewind();
        buffer.get(bytes, 0, readBytes);
        buffer.clear();

        return bytes;
    }
}
