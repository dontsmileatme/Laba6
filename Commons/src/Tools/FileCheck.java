package Tools;

import java.io.File;

public class FileCheck {

    public static int checkFile(String filename) {
        File file = new File(filename);

        if (file.isDirectory()) {
            System.out.println("Необходимо указать полное имя файла, а не директорию.");
            return -1;
        }

        if (!file.exists()) {
            System.out.println("Файл не найден.");
            return -1;
        }

        if (!file.canRead() && !file.canWrite()) {
            System.out.println("Ошибка доступа чтения и записи файла.");
            return -1;
        }

        if (!file.canRead()) {
            System.out.println("Ошибка доступа на чтение файла.");
            return -1;
        }

        if (!file.canWrite()) {
            System.out.println("Ошибка доступа на запись файла.");
            return -1;
        }

        return 0;
    }


}