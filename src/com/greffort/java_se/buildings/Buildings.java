package com.greffort.java_se.buildings;

import com.sun.istack.internal.NotNull;
import com.greffort.java_se.factory.BuildingFactory;
import com.greffort.java_se.factory.DwellingFactory;
import com.greffort.java_se.interfaces.Building;
import com.greffort.java_se.interfaces.Floor;
import com.greffort.java_se.interfaces.Space;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class Buildings implements Serializable {

    private static BuildingFactory factory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory factory) {
        Buildings.factory = factory;
    }

    public static Space createSpace(double area) {
        return factory.createSpace(area);
    }

    public static Space createSpace(double area, int roomsCount) {
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

//region reflect create (Lab 10 ex.2)
//    public static Space createSpace(double area, Class spaceClass)

    /**
     * Методы должны найти в предложенном классе конструктор с необходимыми типами параметров и
     * создать объект с помощью этого конструктора (а не с помощью фабрики).
     * Если по каким-то причинам создание объекта невозможно (класс не реализует нужный интерфейс, отсутствует
     * конструктор с нужными параметрами и т.д.), методы должны выбрасывать исключение IllegalArgumentException.
     * При выполнении задания учитывать параметризованность механизмов рефлексии, то есть методы принимают
     * параметризованный параметр типа Class, а для создания объекта используется параметризованная
     * версия класса Constructor.
     */
    public static Space createSpace(double area, Class<? extends Space> spaceClass) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<? extends Space> constructor = spaceClass.getConstructor(spaceClass.getClass(), double.class);
        return constructor.newInstance(area);
    }

    public static Space createSpace(double area, int roomsCount, Class<? extends Space> spaceClass)
            throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Constructor<? extends Space> constructor = spaceClass.getConstructor(spaceClass.getClass(), double.class, int.class);
        return constructor.newInstance(roomsCount, area);
    }

    public static Floor createFloor(int spacesCount, Class/*<Floor>*/ floorClass) throws IllegalArgumentException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Floor> constructor = floorClass.getConstructor(floorClass.getClass(), int.class);
        return constructor.newInstance(spacesCount);
    }

    public static Floor createFloor(Space[] spaces, Class/*<Floor>*/ floorClass) throws IllegalArgumentException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Floor> constructor = floorClass.getConstructor(floorClass.getClass(), Space[].class);
        return constructor.newInstance(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts, Class/*<Building>*/ buildingClass)
            throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Building> constructor = buildingClass.getConstructor(buildingClass.getClass(), int.class, int[].class);
        return constructor.newInstance(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors, Class/*<Building>*/ buildingClass) throws IllegalArgumentException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Building> constructor = buildingClass.getConstructor(buildingClass.getClass(), Floor[].class);
        return constructor.newInstance(floors);
    }

// endregion

//region reflect readBuilding (Lab 10 ex.3)
    //public static Building inputBuilding(InputStream in, Class buildingClass, Class floorClass, Class spaceClass).

    public static Building inputBuilding(InputStream in, Class<Building> buildingClass,
                                         Class<Floor> floorClass, Class<Space> spaceClass) throws IOException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DataInputStream dataInputStream = new DataInputStream(in);
        Floor[] floors = new Floor[dataInputStream.readInt()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[dataInputStream.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = createSpace(dataInputStream.readDouble(), dataInputStream.readInt(), spaceClass);
            }
            floors[i] = createFloor(spaces, floorClass);
        }
        return createBuilding(floors, buildingClass);
    }

    public static Building readBuilding(Reader in, Class<Building> buildingClass,
                                        Class<Floor> floorClass, Class<Space> spaceClass) throws IOException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);

        if (streamTokenizer.nextToken() == StreamTokenizer.TT_EOL) {
            return null;
        }
        Floor[] floors = new Floor[(int) streamTokenizer.nval];
        for (int i = 0; i < floors.length; i++) {
            streamTokenizer.nextToken();
            Space[] spaces = new Space[(int) streamTokenizer.nval];
            for (int j = 0; j < spaces.length; j++) {
                streamTokenizer.nextToken();
                int number2 = ((int) streamTokenizer.nval);
                streamTokenizer.nextToken();
                double number1 = streamTokenizer.nval;
                spaces[j] = createSpace(number1, number2, spaceClass);
            }
            floors[i] = createFloor(spaces, floorClass.getClass());
        }
        return createBuilding(floors, buildingClass.getClass());
    }

    public static Building readBuilding(Scanner scanner, Class<Building> buildingClass,
                                        Class<Floor> floorClass, Class<Space> spaceClass)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Floor[] floors;
        floors = new Floor[scanner.nextInt()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[scanner.nextInt()];
            for (int j = 0; j < spaces.length; j++) {
                int room = scanner.nextInt();
                double square = scanner.nextDouble();
                spaces[j] = createSpace(square, room, spaceClass);
            }
            floors[i] = createFloor(spaces, floorClass.getClass());
        }
        return createBuilding(floors, buildingClass.getClass());
    }
//endregion

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

        if (streamTokenizer.nextToken() == streamTokenizer.TT_EOL) {
            return null;
        }
        Floor[] floors = new Floor[(int) streamTokenizer.nval];
        for (int i = 0; i < floors.length; i++) {
            streamTokenizer.nextToken();
            Space[] spaces = new Space[(int) streamTokenizer.nval];
            for (int j = 0; j < spaces.length; j++) {
                streamTokenizer.nextToken();
                int number2 = ((int) streamTokenizer.nval);
                streamTokenizer.nextToken();
                double number1 = streamTokenizer.nval;

                spaces[j] = createSpace(number1, number2);
            }
            floors[i] = createFloor(spaces);
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
//            int a = scanner.nextInt();
            Space[] spaces = new Space[scanner.nextInt()];
            for (int j = 0; j < spaces.length; j++) {
//                scanner.nextInt();
//                scanner.nextInt();
                int room = scanner.nextInt();
                double square = scanner.nextDouble();
                spaces[j] = createSpace(square, room);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

//    public static Building readBuilding(Scanner scanner) throws IOException {
//        Floor[] floors;
//        floors = new Floor[scanner.nextInt()];
//        for (int i = 0; i < floors.length; i++) {
//            Space[] spaces = new Space[scanner.nextInt()];
//            for (int j = 0; j < spaces.length; j++) {
//                int room = scanner.nextInt();
//                double square = scanner.nextDouble();
//                spaces[j] = createSpace(square,room);
//            }
//            floors[i] = createFloor(spaces);
//        }
//        return createBuilding(floors);
//    }

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
                if (floors[j].compareTo(floors[j + 1]) > 0) {
                    E tmp = floors[j + 1];
                    floors[j + 1] = floors[j];
                    floors[j] = tmp;
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] floors, Comparator<E> c) {
        /**
         * В класс Buildings добавьте два метода сортировки с критерием – сортировка помещений на этаже по
         * убыванию количества комнат и сортировка этажей в здании по убыванию общей площади помещений этажа.
         * Объедините оба метода в один параметризованный метод сортировки с критерием.
         */
        for (int i = floors.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (c.compare(floors[j], floors[j + 1]) > 0) {
                    E tmp = floors[j + 1];
                    floors[j + 1] = floors[j];
                    floors[j] = tmp;
                }
            }
        }
    }

//    public static <E extends Comparable<E>> void sort1(E[] floors,  Comparator<E> c) {
//        //public static <E> void sort(E[] floors, Comparator<? super E> c) {
//        /**
//         * В класс Buildings добавьте два метода сортировки с критерием – сортировка помещений на этаже по
//         * убыванию количества комнат и сортировка этажей в здании по убыванию общей площади помещений этажа.
//         * Объедините оба метода в один параметризованный метод сортировки с критерием.
//         */
////        (E e1, E e2) ->
//
//        for (int i = floors.length - 1; i > 0; i--) {
//            for (int j = 0; j < i; j++) {
//                (floors[j], floors[j + 1]) ->
//                if (c.compare(floors[j], floors[j + 1]) > 0) {
//                    E tmp = floors[j + 1];
//                    floors[j + 1] = floors[j];
//                    floors[j] = tmp;
//                }
//            }
//        }
//    }


    /*
     * Добавьте в класс Buildings со статическими методами обработки реализацию метода
     * Floor synchronizedFloor (Floor floor),
     * возвращающего ссылку на оболочку указанного объекта этажа, безопасную с точки зрения многопоточности.
     *
     * Для этого потребуется в пакете com.greffort.buildings описать новый класс декоратора SynchronizedFloor, реализующий
     * с обеспечением синхронизации методы интерфейса Floor, а также перегружающий ряд методов класса Object.
     * Создание специальных итераторов и их синхронизация не требуются
     */

    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }
}
