package Tools;

import Data.CommandShell;
import Data.CommandShellsBundle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Deserializer {
    public static <T extends Serializable> T toDeserialize(byte[] bytes, Class<T> clazz) {
        if (bytes != null) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Object object = objectInputStream.readObject();
                return clazz.cast(object);
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                e.printStackTrace();
                System.err.println("Ошибка десериализации.");
                return null;
            }
        } else {
            System.err.println("Принятое сообщение - пустое.");
            return null;
        }
    }

    public static int flagCheck(byte[] bytes) {
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
            Object object = objectInputStream.readObject();
            if (CommandShell.class.isInstance(object)) {
                return 1;
            } else if (CommandShellsBundle.class.isInstance(object)) {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка десериализации.");
        }
        return -1;
    }
}
