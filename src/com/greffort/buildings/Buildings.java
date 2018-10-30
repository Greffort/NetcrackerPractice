package com.greffort.buildings;

import com.greffort.buildings.dwelling.Flat;
import com.greffort.buildings.office.Office;
import com.greffort.buildings.office.OfficeBuilding;
import com.greffort.buildings.office.OfficeFloor;
import com.greffort.factory.OfficeFactory;
import com.greffort.factory.Factory;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;


public class Buildings implements Serializable {
    /**
     * //      Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building,
     * //      содержащий статические методы ввода-вывода зданий:
     * <p>
     * //          •	записи данных о здании в байтовый поток
     * //      public static void outputBuilding (Building office, OutputStream out);
     * <p>
     * //          •	чтения данных о здании из байтового потока
     * //      public static Building inputBuilding (InputStream in);
     * <p>
     * //          •	записи здания в символьный поток
     * //      public static void writeBuilding (Building office, Writer out);
     * <p>
     * //          •	чтения здания из символьного потока
     * //      public static Building readBuilding (Reader in).
     * <p>
     * //      Записанные данные о здании представляет собой последовательность чисел, первым из которых является количество этажей,
     * //      далее следует количество помещений текущего этажа и соответствующие значения количества комнат и площадей помещений текущего этажа.
     * <p>
     * //      Например, символьная запись для трехэтажного здания:
     * //          «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»
     * <p>
     * //      Для чтения этажа из символьного потока можно использовать StreamTokenizer.
     * //      Проверьте возможности всех реализованных методов, в качестве реальных потоков используя файловые потоки,
     * //      а также потоки System.in и System.out.
     * <p>
     * ---------------------------------------------------------
     * Добавьте в класс Buildings следующие методы:
     * •	сериализации здания в байтовый поток
     * public static void serializeBuilding (Building office, OutputStream out);
     * •	десериализации здания из байтового потока
     * public static Building deserializeBuilding (InputStream in);
     * Добавьте метод текстовой записи
     * public static void writeBuildingFormat (Building office, Writer out)
     * для зданий класса Buildings, использующий возможности форматированного вывода.
     * Перегрузите метод текстового чтения зданий класса Buildings таким образом,
     * чтобы он использовал возможности форматированного ввода и вывода и имел аргумент типа Scanner.
     */
    private static Factory factory = new OfficeFactory(); // сделать выбор фабрики

    public static void setFactory(Factory factory) {
        Buildings.factory = factory;
    }

    public static Building createBuilding(Floor[] floors) {
        return factory.createBuilding(floors);
    }


    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        // Сделать запись первым байтом - тип здания, тип этажа, тип помещения
        //write count floor on office
        dataOutputStream.writeInt(building.getCountFloors());
        for (int i = 0, countFloors = building.getCountFloors(); i < countFloors; i++) {
            //write count spaces on floor
            dataOutputStream.writeInt(building.getFloors()[i].getCountSpace());
            for (int j = 0, countSpaces = building.getFloors()[i].getCountSpace(); j < countSpaces; j++) {
                //write count room
                dataOutputStream.writeDouble(building.getFloors()[i].getSpaces()[j].getSquare());
                dataOutputStream.writeInt(building.getFloors()[i].getSpaces()[j].getRoomCount());
                //write space

            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        //count Floors
        // Space space = null;
        Floor[] floors = new Floor[dataInputStream.readInt()];
        for (int i = 0; i < floors.length; i++) {
            //count Spaces on Floor
            Space[] spaces = new Space[dataInputStream.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = factory.createSpace(dataInputStream.readDouble(), dataInputStream.readInt());
            }
            floors[i] = factory.createFloor(spaces);
        }
//        dataInputStream.close();
        return factory.createBuilding(floors);
    }

    public static void writeBuilding(Building building, Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.print(building.getCountFloors() + " ");
        for (int i = 0, countFloors = building.getCountFloors(); i < countFloors; i++) {
            printWriter.print(building.getFloors()[i].getCountSpace() + " ");
            for (int j = 0, countSpaces = building.getFloors()[i].getCountSpace(); j < countSpaces; j++) {
                printWriter.print(building.getFloors()[i].getSpaces()[j].getSquare() + " ");
                printWriter.print(building.getFloors()[i].getSpaces()[j].getRoomCount() + " ");
            }
        }
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        streamTokenizer.nextToken();
        Floor[] floors = new Floor[(int) streamTokenizer.nval];
        for (int i = 0; i < floors.length; i++) {
            streamTokenizer.nextToken();
            Space[] spaces = new Space[(int) streamTokenizer.nval];
            for (int j = 0; j < spaces.length; j++) {
                streamTokenizer.nextToken();
                double number1 = streamTokenizer.nval;
                streamTokenizer.nextToken();
                int number2 = ((int) streamTokenizer.nval);
                spaces[j] = factory.createSpace(number1, number2);
            }
            floors[i] = factory.createFloor(spaces);
        }
        return factory.createBuilding(floors);

    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(building);

    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        return (Building) objectInputStream.readObject();
    }

    /**
     * Добавьте метод текстовой записи
     * public static void writeBuildingFormat (Building office, Writer out)
     * для зданий класса Buildings,
     * использующий возможности форматированного вывода.
     * <p>
     * Перегрузите метод текстового чтения зданий класса Buildings таким образом,
     * чтобы он использовал возможности форматированного ввода и вывода и имел аргумент типа Scanner.
     */
    public static void writeBuildingFormat(@NotNull Building building, Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.printf(Locale.ENGLISH, "Количество этажей: %d %n", building.getCountFloors());
        for (int i = 0, countFloors = building.getCountFloors(); i < countFloors; i++) {
            printWriter.printf(Locale.ENGLISH, "Количество помещений на этаже #%d: %d %n", i, +building.getFloors()[i].getCountSpace());
            for (int j = 0, countSpaces = building.getFloors()[i].getCountSpace(); j < countSpaces; j++) {
                printWriter.printf(Locale.ENGLISH, "Площадь %d-го помещения:  %-3.2f|", j, building.getFloors()[i].getSpaces()[j].getSquare());
                printWriter.printf(Locale.ENGLISH, "Количество комнат %d-го помещения: %d %n", j, building.getFloors()[i].getSpaces()[j].getRoomCount());
            }
        }
    }

    public static Building readBuilding(Scanner scanner) throws IOException {
        Floor[] floors;
        //scanner = new Scanner(in).useDelimiter("\\W+");

        floors = new Floor[scanner.nextInt()];

        for (int i = 0; i < floors.length; i++) {
            scanner.nextInt();
            Space[] spaces = new Space[scanner.nextInt()];

            for (int j = 0; j < spaces.length; j++) {
                scanner.nextInt();
                scanner.nextInt();
                spaces[j] = factory.createSpace(scanner.nextInt(), scanner.nextInt());
            }
            floors[i] =factory.createFloor(spaces);
        }
        return new OfficeBuilding(floors);
    }
}
