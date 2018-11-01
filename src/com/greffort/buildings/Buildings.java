package com.greffort.buildings;

import com.greffort.buildings.office.OfficeBuilding;
import com.greffort.factory.DwellingFactory;
import com.greffort.factory.OfficeFactory;
import com.greffort.factory.BuildingFactory;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;


public class Buildings<E> implements Serializable {

    private static BuildingFactory factory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory factory) {
        Buildings.factory = factory;
    }

    public static Space createSpace(double area) {
        return factory.createSpace(area);
    }

    public static Space createSpace( double area,int roomsCount) {
        return factory.createSpace(area, roomsCount);
    }

    public static Floor createFloor(int spacesCount) {
        return factory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return factory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return factory.createBuilding(floorsCount, spacesCounts);
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
                spaces[j] = createSpace(dataInputStream.readDouble(), dataInputStream.readInt());
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
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
                spaces[j] = createSpace(number1, number2);
            }
            floors[i] =createFloor(spaces);
        }
        return createBuilding(floors);

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
                spaces[j] = createSpace(scanner.nextInt(), scanner.nextInt());
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    public static <E extends Comparable<E>> void sort(E[] floors) {
        /**
         * //Добавьте в класс Buildings метод сортировки помещений этажа по возрастанию площадей помещений,
         * // и метод сортировки этажей здания по возрастанию количества помещений на этаже.
         *
         * положительное, если вызывающий объект больше объекта, переданного в качестве параметра;
         * отрицательное, если вызывающий объект меньше объекта, переданного в качестве параметра;
         * нуль, если объекты равны.
         */
        for (int i = floors.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (floors[j].compareTo(floors[j + 1]) < 0) {
                    E tmp = floors[j];
                    floors[j] = floors[j + 1];
                    floors[j + 1] = tmp;
                }
                if (floors[j].compareTo(floors[j + 1]) > 0) {
                    E tmp = floors[j + 1];
                    floors[j + 1] = floors[j];
                    floors[j] = tmp;
                }
            }
        }
    }

    public static <E> void sort(E[] floors, Comparator<? super E> c) {
        /**
         * В класс Buildings добавьте два метода сортировки с критерием – сортировка помещений на этаже по
         * убыванию количества комнат и сортировка этажей в здании по убыванию общей площади помещений этажа.
         * Объедините оба метода в один параметризованный метод сортировки с критерием.
         */
        for (int i = floors.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (c.compare(floors[j], floors[j + 1]) < 0) {
                    E tmp = floors[j];
                    floors[j] = floors[j + 1];
                    floors[j + 1] = tmp;
                }
                if (c.compare(floors[j], floors[j + 1]) > 0) {
                    E tmp = floors[j + 1];
                    floors[j + 1] = floors[j];
                    floors[j] = tmp;
                }
            }
        }
    }


}
