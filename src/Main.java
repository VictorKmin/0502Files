import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        //Створив файл з імям test.txt
        File file = new File("test.txt");
        //Віддаю методу який записує шось в створений файл з можливістю перезапису
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        //Записую в файл значення переведені в масив байтів
        fileOutputStream.write("iiiiii".getBytes());
        fileOutputStream.write("hhhhhhh".getBytes());
        fileOutputStream.write("1234".getBytes());
        //Закриваю потім запису ОБОВЯЗКОВО
        fileOutputStream.flush();
        fileOutputStream.close();

        //Методі який віддає мені інформацію з файлу
        FileInputStream fileInputStream = new FileInputStream(file);
        //Заглушка, яка не дає вилізти за межі значень файлу
        int x;
        while((x = fileInputStream.read()) != -1) {
            //Виводжу все в char змість байт коду
            System.out.println((char) x);
        }

        //Аналог outputStream
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("HelloJava");
        fileWriter.flush();
        fileWriter.close();


        //Аналог inputStream
        FileReader fileReader = new FileReader(file);

        //  Пряцює як inputStream
        System.out.println(fileReader.read());


        //Аналог outputStream
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        //Записує з нової строчки інформацію
        printWriter.println("Hello");
        printWriter.println("Java");
        //Закриваю потік. НЕ ЗАБУВАТИ
        printWriter.flush();
        printWriter.close();

        // аналог input
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        //Роблю заглушку
        String acc;
        //Поки заглушка має інфу - вивожити її
        while ((acc = bufferedReader.readLine()) != null) {
            System.out.println(acc);
        }

        //Методі який записує обєкти в файл. Приймає в себе OutputStream
        // ЩОБ ЗАПИСАТИ ОБЄКТ ВІН МАЄ ІМПЛЕМЕНТУВАТИ Serializable !!!!!!!!!!!!!!!!!!!!!!!!1
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        // Записую обєкт
        objectOutputStream.writeObject(new Car(0, "Cara"));
        //Закриваю поітк
        objectOutputStream.flush();
        objectOutputStream.close();


        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        // Маніпуляції, шо б зчитати обєкт.
        Car car = (Car) objectInputStream.readObject();
        //Далі маніпулюємо з ним як завгодно
        car.toString();
        System.out.println(car);




        //РОБОТА З ПАПКАМИ і System Properties

        // Створюю файл, який приймає системну властивість домашньої папки. Повертає всю папку
        File UserHomeFile = new File(System.getProperty("user.home"));
        // Якшо файл є папкою тоді ....
        if (UserHomeFile.isDirectory()){
            //Видати цю папку як ліст
            List<File> fileList = Arrays.asList(UserHomeFile.listFiles());
            //iter
            for (File file1 : fileList) {
//                //Виводимо всі папки і файли які є
//                System.out.println(file1);
                // Якщо файл є НЕ дерикторією
                if (!file.isDirectory()){
                    //Вивести довжину файлу
                    System.out.println(file1.length());
                }
            }
        }

        //ФІльтрація файлів
        //Створюєм метод фільтрації в лісті нашого фалу
        UserHomeFile.listFiles(new FilenameFilter() {
            @Override
            // Override дописує автоматично !
            //dir - шлях до папки; name - імя папки
            public boolean accept(File dir, String name) {
                System.out.println(dir + " ");
                System.out.println(name);
                return true;
            }
        });

        //Створюю ліст файлів з імям fileList
        //Кажу шо це буде Масив як ліст
        //З фалйу UserHomeFiles, записаний лістом файлів
        // контрал Шифт пробіл і вибираю лямбу FilenameFilter
        List<File> fileList = Arrays.asList(UserHomeFile.listFiles((dir, name) ->{
            // Якшо name має кінець .ini верни тру. Якщо ні верни фолс
            return name.endsWith(".ini")? true:false;
        }));
        //дляКожного з dileList зробити sout
        fileList.forEach(System.out::println);
    }
}
