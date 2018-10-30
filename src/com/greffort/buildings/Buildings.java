package com.greffort.buildings;

import com.greffort.buildings.office.OfficeBuilding;
import com.greffort.factory.OfficeFactory;
import com.greffort.factory.Factory;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;


public class Buildings implements Serializable {

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
        dataOutputStream.writeInt(building.getCountFloors());
        for (int i = 0, countFloors = building.getCountFloors(); i < countFloors; i++) {
            dataOutputStream.writeInt(building.getFloors()[i].getCountSpace());
            for (int j = 0, countSpaces = building.getFloors()[i].getCountSpace(); j < countSpaces; j++) {
                dataOutputStream.writeDouble(building.getFloors()[i].getSpaces()[j].getSquare());
                dataOutputStream.writeInt(building.getFloors()[i].getSpaces()[j].getRoomCount());
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        Floor[] floors = new Floor[dataInputStream.readInt()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[dataInputStream.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = factory.createSpace(dataInputStream.readDouble(), dataInputStream.readInt());
            }
            floors[i] = factory.createFloor(spaces);
        }
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
        floors = new Floor[scanner.nextInt()];
        for (int i = 0; i < floors.length; i++) {
            scanner.nextInt();
            Space[] spaces = new Space[scanner.nextInt()];
            for (int j = 0; j < spaces.length; j++) {
                scanner.nextInt();
                scanner.nextInt();
                spaces[j] = factory.createSpace(scanner.nextInt(), scanner.nextInt());
            }
            floors[i] = factory.createFloor(spaces);
        }
        return new OfficeBuilding(floors);
    }

    //Добавьте в класс Buildings метод сортировки помещений этажа по возрастанию площадей помещений,
    // и метод сортировки этажей здания по возрастанию количества помещений на этаже.

    public Floor sort(Floor floors) {
        /**
         * //Добавьте в класс Buildings метод сортировки помещений этажа по возрастанию площадей помещений,
         * // и метод сортировки этажей здания по возрастанию количества помещений на этаже.
         *
         * положительное, если вызывающий объект больше объекта, переданного в качестве параметра;
         * отрицательное, если вызывающий объект меньше объекта, переданного в качестве параметра;
         * нуль, если объекты равны.
         */

        Space[] spaces = new Space[floors.getCountSpace()];
        for (int i = 0, j = 1; j < floors.getCountSpace(); i++) {

            floors.getSpace(i).compareTo(floors.getSpace(j));

            if (floors.getSpace(i).compareTo(floors.getSpace(j)) > 0) {
                Space s = floors.getSpace(i);
                floors.setSpace(floors.getSpace(j),i);
                floors.setSpace(s,j);
            }
            if (floors.getSpace(i).compareTo(floors.getSpace(j)) < 0) {

            } else {

            }

        }
        for (Space space : floors) {
            if (space.compareTo(space) > 0) {

            }
            if (space.compareTo(space) < 0) {

            } else {

            }
        }
        return factory.createFloor(spaces);
    }

    public int sort(Floor floor) {
        /**
         *  //Добавьте в класс Buildings метод сортировки помещений этажа по возрастанию площадей помещений,
         *  // и метод сортировки этажей здания по возрастанию количества помещений на этаже.
         * Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед тем, который передается
         * через параметр.
         * Если метод вернет положительное число, то, наоборот, после второго объекта.
         * Если метод возвратит ноль, значит, оба объекта равны.
         */
        if (this.getCountSpace() > floor.getCountSpace()) {
            return 1;
        }
        if (this.getCountSpace() < floor.getCountSpace()) {
            return -1;
        } else {
            return 0;
        }
    }
}
