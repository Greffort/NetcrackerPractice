package com.greffort.java_se.main;

import com.greffort.java_se.buildings.Buildings;
import com.greffort.java_se.buildings.dwelling.Dwelling;
import com.greffort.java_se.buildings.dwelling.DwellingFloor;
import com.greffort.java_se.buildings.dwelling.Flat;
import com.greffort.java_se.buildings.dwelling.HotelFloor;
import com.greffort.java_se.buildings.office.Office;
import com.greffort.java_se.buildings.office.OfficeBuilding;
import com.greffort.java_se.buildings.office.OfficeFloor;
import com.greffort.java_se.comparators.ComparatorSpace;
import com.greffort.java_se.factory.HotelFactory;
import com.greffort.java_se.interfaces.Building;
import com.greffort.java_se.interfaces.Floor;
import com.greffort.java_se.interfaces.Space;

import java.io.*;

import static java.lang.Double.NaN;

//import java.lang.*;


public class Main0001 {

    public static void main(String[] args) {
//        Flat[] flats1 = new Flat[]{new Flat(11, 1), new Flat(12, 2), new Flat(13, 3), new Flat(14, 3)};
////        Flat[] flats2 = new Flat[]{new Flat(21, 1), new Flat(22, 2), new Flat(23, 3), new Flat(24, 3)};
////        Flat[] flats3 = new Flat[]{new Flat(31, 1), new Flat(32, 2), new Flat(33, 3), new Flat(34, 3)};
////        Flat[] flats33 = new Flat[]{new Flat(31, 1), new Flat(32, 2), new Flat(33, 3), new Flat(34, 3)};
////        DwellingFloor dwellingFloor1 = new DwellingFloor(flats1);
////        DwellingFloor dwellingFloor2 = new DwellingFloor(flats2);
////        DwellingFloor dwellingFloor3 = new DwellingFloor(flats3);
////        DwellingFloor dwellingFloor33 = new DwellingFloor(flats33);
////
////        DwellingFloor[] dwellings = new DwellingFloor[]{dwellingFloor1, dwellingFloor2, dwellingFloor3};
////
////        Dwelling dwelling = new Dwelling(dwellings);

        Flat[] flats1 = new Flat[]{new Flat(11, 1)};
        Flat[] flats2 = new Flat[]{new Flat(21, 1)};
        Flat[] flats3 = new Flat[]{new Flat(31, 1)};
        Flat[] flats33 = new Flat[]{new Flat(31, 1)};
        DwellingFloor dwellingFloor1 = new DwellingFloor(flats1);
        DwellingFloor dwellingFloor2 = new DwellingFloor(flats2);
        DwellingFloor dwellingFloor3 = new DwellingFloor(flats3);
        DwellingFloor dwellingFloor33 = new DwellingFloor(flats33);

        DwellingFloor[] dwellings = new DwellingFloor[]{dwellingFloor1, dwellingFloor2, dwellingFloor3};

        Dwelling dwelling = new Dwelling(dwellings);

        System.out.println(dwellingFloor1.toString());
        System.out.println(dwellingFloor2.toString());
        System.out.println(dwelling.toString());
        int a = 0;

        dwelling.toString();
////        dwelling.addSpace(new Flat(100500, 100500),13);
////
////        Space[] flats = dwelling.getSortSpaces();
////
////        for (Space f : flats
////        ) {
////            System.out.println(f.getSquare() + " " + f.getRoomCount());
////        }
////
////
////        System.out.println("\n");
////        dwelling.removeSpace(0);
////        Flat[] flats11 = dwelling.getSortSpaces();
////
////        for (Flat f : flats11
////        ) {
////            System.out.println(f.getSquare() + " " + f.getRoomCount());
////        }
//
//        //Office office = new Office();
//        //OfficeFloor officeFloor =new OfficeFloor(4);
////        System.out.println(officeFloor.getTotalSquare());
////        officeFloor.getTotalSquare();
////        System.out.println(officeFloor.getTotalRoomCount());
////        Office[] o = officeFloor.getSpaces();
////        System.out.println(officeFloor.getTotalRoomCount());
////
////        Office offfffice = officeFloor.getSpace(0);
////        System.out.println(officeFloor.getSpace(0));
////        System.out.println(officeFloor.getTotalRoomCount());
////
////        officeFloor.setSpace(new Office(1,1),0);
////        System.out.println(officeFloor.getTotalRoomCount());
//
//        OfficeFloor officeFloor = new OfficeFloor(0);
//
//        officeFloor.addSpace(new Office(1, 1), 0);
//        officeFloor.addSpace(new Office(2, 2), 1);
//        officeFloor.addSpace(new Office(3, 3), 2);
//        officeFloor.addSpace(new Office(4, 4), 3);
//        //officeFloor.addSpace(new Office(100500,100500),3);
//
//        //System.out.println(officeFloor.getSpace(3).getRoomCount());
////
////officeFloor.removeSpace(2);
//
//        //System.out.println(officeFloor.getSpace(4).getRoomCount());
//
//        //officeFloor.setSpace(new Office(),3);
////        System.out.println(officeFloor.getSpace(3).getSquare());
////        //officeFloor.print();
////        officeFloor.getSpace(0);
////        officeFloor.getSpace(1);
////        officeFloor.getSpace(2);
////        officeFloor.getSpace(3);
//
        OfficeFloor OfficeFloor1 = new OfficeFloor(new Office[]{new Office(31, 11), new Office(30, 12), new Office(1, 13)});
        OfficeFloor OfficeFloor2 = new OfficeFloor(new Office[]{new Office(21, 21), new Office(22, 22), new Office(23, 23)});
        OfficeFloor OfficeFloor3 = new OfficeFloor(new Office[]{new Office(31, 31), new Office(32, 32), new Office(33, 33)});
//
//        //OfficeFloor1.removeSpace(0);
//        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{OfficeFloor1, OfficeFloor2, OfficeFloor3});
//
//        Space o1 = officeBuilding.getSpace(6);
//        officeBuilding.setSpace(new Office(), 6);
//        Space o2 = officeBuilding.getSpace(6);
//
//
//        officeBuilding.addSpace(new Office(100500, 900500), 1);
//        //Space o3 = officeBuilding.getSpace(9);
//
//
////
////        officeBuilding.removeSpace(6);
//        Space o4 = officeBuilding.getSpace(1);
////
//        officeBuilding.addSpace(new Flat(), 1);
////
////        Office o5 = officeBuilding.getBestSpace();
//
//        Space[] o6 = officeBuilding.getSortSpaces();
//
//        Space o7 = officeBuilding.getFloor(2).getSpace(0);
//        officeFloor.getSpace(0);

/**
 * Проверка 3 задания 4 лабораторной
 * Ввод вывод. Байтовые, символьные потоки
 */
        checkByteSymbolInOutput();
/**
 * Проверка работы сереализации
 */
        checkSerializableWork();
/**
 * Проверка работы форматированного ввода
 */
        checkWriteFormat();
//
//        OfficeFloor OfficeFloor11111 = new OfficeFloor(new Office[]{new Office(11, 110), new Office(12, 120), new Office(13, 130)});
//        OfficeFloor OfficeFloor21111 = new OfficeFloor(new Office[]{new Office(21, 210), new Office(22, 220), new Office(23, 230)});
//        OfficeFloor OfficeFloor31111 = new OfficeFloor(new Office[]{new Office(31, 310), new Office(32, 320), new Office(33, 330)});
//
//        OfficeFloor OfficeFloor1 = new OfficeFloor(new Office[]{new Office(11, 110), new Office(12, 120), new Office(13, 130)});
//        OfficeFloor OfficeFloor2 = new OfficeFloor(new Office[]{new Office(21, 210), new Office(22, 220), new Office(23, 230)});
//        OfficeFloor OfficeFloor3 = new OfficeFloor(new Office[]{new Office(7771, 310), new Office(32, 320), new Office(33, 330)});
//
//
//        OfficeBuilding officeBuilding11 = new OfficeBuilding(new OfficeFloor[]{OfficeFloor11111, OfficeFloor21111, OfficeFloor31111});
//        OfficeBuilding officeBuilding12 = new OfficeBuilding(new OfficeFloor[]{OfficeFloor1, OfficeFloor2, OfficeFloor31111});
//        OfficeBuilding officeBuilding13 = new OfficeBuilding(new OfficeFloor[]{OfficeFloor1, OfficeFloor2, OfficeFloor31111});
//
//        OfficeFloor OfficeFloor11 = new OfficeFloor(new Office[]{new Office(11, 110), new Office(12, 120), new Office(13, 130)});
//
//        System.out.println(OfficeFloor21111.toString());
//        System.out.println(OfficeFloor31111.toString());
//        System.out.println(officeBuilding11.toString());
//
//        boolean b11 =dwellingFloor1.equals(dwellingFloor1);
//        boolean b12 =dwellingFloor1.equals(dwellingFloor2);
//        boolean b13 =dwellingFloor1.equals(dwellingFloor3);
//        boolean b14 =dwellingFloor3.equals(dwellingFloor33);
//
//        boolean b21 =OfficeFloor11111.equals(OfficeFloor11111);
//        boolean b22 =OfficeFloor11111.equals(OfficeFloor21111);
//        boolean b23 =OfficeFloor11111.equals(OfficeFloor31111);
//        boolean b24 =OfficeFloor11111.equals(OfficeFloor1);
//
//        boolean b31 =officeBuilding11.equals(officeBuilding12);
//        boolean b32 =officeBuilding11.equals(OfficeFloor3);
//        boolean b33 =officeBuilding11.equals(officeBuilding13);
//
//        Flat flat = new Flat();
//        Flat flat1 = new Flat(50.0,2);
//
//        Office office = new Office(50.0,2);
//
//        int hashFlat = flat.hashCode();
//        int hashFlat2 = flat1.hashCode();
//        int gashOffice1 = office.hashCode();
//
//
//        DwellingFloor dwellingFloor11 = new DwellingFloor(flats1);
//        DwellingFloor dwellingFloor22 = new DwellingFloor(flats2);
//
//        int hashDW1 = dwellingFloor11.hashCode();
//        int hashDW2 =dwellingFloor22.hashCode();
//
//        int hashOB1 = OfficeFloor1.hashCode();
//        int hashOB2 =  OfficeFloor21111.hashCode();
//        int hashOB3 =  OfficeFloor11111.hashCode();
//
//
//
//
//        //////////////////
//        System.out.println("Проверка смены помещений");
//
//        try {
//            PlacementExchanger.exchangeFloorRooms(officeBuilding11.getFloor(2), 1, officeBuilding12.getFloor(2), 1);
//            System.out.println("Обмен помещениями прошел успешно.");
//        }
//        catch (IndexChangeableSpacesException e) {
//            System.out.println(e.getMessage());
//        }
//
//        catch (SpaceIndexOutOfBoundsException | FloorIndexOutOfBoundsException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Конец проверки смены помещений\n");

        HotelFloor hotelFloor = new HotelFloor(2);

        DwellingFloor spaces = new DwellingFloor(flats1);

//        for (Space space:spaces)
//        {
//            System.out.println(space.getSquare()+" 1");
//        }
        spaces.getBestSpace();


        OfficeFloor spaces1 = new OfficeFloor(flats1);

        spaces1.getBestSpace();

//        for (Space space :
//                spaces1) {
//            System.out.println(space.getSquare() +"2");
//        }

        OfficeBuilding floors = new OfficeBuilding(new Floor[]{OfficeFloor1, OfficeFloor2, OfficeFloor3});
        Building building = new OfficeBuilding(new Floor[]{OfficeFloor1, OfficeFloor2, OfficeFloor3});
        Floor floor1 = new OfficeFloor(1);
        for (Floor floor :
                building) {

        }
        for (Space floor :
                floor1) {

        }

        floors.addSpace(new Office(), 1);
        Floor floor = floors.getFloor(0);

        Space[] spaces2 = new Space[]{new Office(1, 4), new Flat(3434, 12), new Office(2, 2)};
        Buildings.sort(spaces2);
        //Buildings.sort(spaces2,new ComparatorSpace());
//Floor floor1 = Buildings.sort(floor);

        Space space = new Flat(NaN, 1);
        Space[] spaces3 = new Space[]{new Flat(2, 1), new Flat(3434, 12), new Office(2, 2)};
        Buildings.sort(spaces3, new ComparatorSpace());

        Buildings.sort(spaces3, Comparable::compareTo);

        Buildings.setBuildingFactory(new HotelFactory());

        Floor floor2 = Buildings.createFloor(spaces3);

        System.out.println(((HotelFloor) floor2).toString());

        ((HotelFloor) floor2).setStars(5);
        System.out.println(((HotelFloor) floor2).toString());

        int f = 0;


    }

    private static void checkByteSymbolInOutput() {
        /**
         * создание оздания для передачи его в байтовый поток
         */
        OfficeFloor OfficeFloor11111 = new OfficeFloor(new Office[]{new Office(11, 110), new Office(12, 120), new Office(13, 130)});
        OfficeFloor OfficeFloor21111 = new OfficeFloor(new Office[]{new Office(21, 210), new Office(22, 220), new Office(23, 230)});
        OfficeFloor OfficeFloor31111 = new OfficeFloor(new Office[]{new Office(31, 310), new Office(32, 320), new Office(33, 330)});
        OfficeBuilding officeBuilding1111 = new OfficeBuilding(new OfficeFloor[]{OfficeFloor11111, OfficeFloor21111, OfficeFloor31111});

        /**
         * Проверка байтового ввода - вывода
         */
        try (FileOutputStream fileOutputStream = new FileOutputStream("D:/workOutPutStream.txt")) {
            Buildings.outputBuilding(officeBuilding1111, fileOutputStream);
        } catch (IOException e) {
            System.out.println("error1");
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:/workOutPutStream.txt"))) {
            Building building1 = Buildings.inputBuilding(bufferedInputStream);
        } catch (IOException e) {
            System.out.println("error1");
        }
        /**
         * Проверка символьного ввода вывода
         */
        try (FileWriter outputStream = new FileWriter("D:/workWrite.txt")) {
            Buildings.writeBuilding(officeBuilding1111, outputStream);
        } catch (IOException e) {
            System.out.println("error1");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:/workWrite.txt"))) {
            Building building = Buildings.readBuilding(bufferedReader);
        } catch (IOException e) {
            System.out.println("error1");
        }
    }

    private static void checkSerializableWork() {
        OfficeFloor OfficeFloor1 = new OfficeFloor(new Office[]{new Office(11, 110), new Office(12, 120), new Office(13, 130)});
        OfficeFloor OfficeFloor2 = new OfficeFloor(new Office[]{new Office(21, 210), new Office(22, 220), new Office(23, 230)});
        OfficeFloor OfficeFloor3 = new OfficeFloor(new Office[]{new Office(31, 310), new Office(32, 320), new Office(33, 330)});
        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{OfficeFloor1, OfficeFloor2, OfficeFloor3});
        Building building;
        try (FileOutputStream fileOutputStream = new FileOutputStream("D:/checkSerializableWork.txt")) {
            Buildings.serializeBuilding(officeBuilding, fileOutputStream);
        } catch (IOException e) {
            System.out.println("Output error IOException ");
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:/checkSerializableWork.txt"))) {
            building = Buildings.deserializeBuilding(bufferedInputStream);
        } catch (IOException e) {
            System.out.println("Input error IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("Input error ClassNotFoundException");
        }
    }

    private static void checkWriteFormat() {

/**
 * создание здания для передачи его в байтовый поток
 */
        OfficeFloor OfficeFloor11111 = new OfficeFloor(new Office[]{new Office(11, 110), new Office(12, 120), new Office(13, 130)});
        OfficeFloor OfficeFloor21111 = new OfficeFloor(new Office[]{new Office(21, 210), new Office(22, 220), new Office(23, 230)});
        OfficeFloor OfficeFloor31111 = new OfficeFloor(new Office[]{new Office(31, 310), new Office(32, 320), new Office(33, 330)});
        OfficeBuilding officeBuilding1111 = new OfficeBuilding(new OfficeFloor[]{OfficeFloor11111, OfficeFloor21111, OfficeFloor31111});

        /**
         * Проверка символьного ввода вывода
         */


        try (FileWriter outputStream = new FileWriter("D:/workWriteFormat.txt")) {
            Buildings.writeBuildingFormat(officeBuilding1111, outputStream);
        } catch (IOException e) {
            System.out.println("error1");
        }

        try (FileReader fileReader = new FileReader("D:/workWrite.txt")) {
            // Scanner scanner = new Scanner(fileReader);
            //Buildings.writeBuildingFormat(scanner);
        } catch (IOException e) {
            System.out.println("error1");
        }


    }

}
