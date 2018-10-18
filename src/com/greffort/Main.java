package com.greffort;

import com.greffort.buildings.Buildings;
import com.greffort.dwelling.*;
import com.greffort.building.*;
import com.greffort.factory.BuildingFactory;
import com.greffort.factory.Factory;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import jdk.internal.util.xml.impl.ReaderUTF8;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        //Dwelling[] dwellingArray = new Dwelling[{}];
//        Flat[] flats1 = new Flat[]{new Flat(11, 1), new Flat(12, 2), new Flat(13, 3), new Flat(14, 3)};
//        Flat[] flats2 = new Flat[]{new Flat(21, 1), new Flat(22, 2), new Flat(23, 3), new Flat(24, 3)};
//        Flat[] flats3 = new Flat[]{new Flat(31, 1), new Flat(32, 2), new Flat(33, 3), new Flat(34, 3)};
//
//        DwellingFloor dwellingFloor1 = new DwellingFloor(flats1);
//        DwellingFloor dwellingFloor2 = new DwellingFloor(flats2);
//        DwellingFloor dwellingFloor3 = new DwellingFloor(flats3);
//
//        DwellingFloor[] dwellings = new DwellingFloor[]{dwellingFloor1, dwellingFloor2, dwellingFloor3};
//
//        Dwelling dwelling = new Dwelling(dwellings);
//
//
//        dwelling.addSpace(new Flat(100500, 100500),13);
//
//        Space[] flats = dwelling.getSortSpaces();
//
//        for (Space f : flats
//        ) {
//            System.out.println(f.getSquare() + " " + f.getRoomCount());
//        }
//
//
//        System.out.println("\n");
//        dwelling.removeSpace(0);
//        Flat[] flats11 = dwelling.getSortSpaces();
//
//        for (Flat f : flats11
//        ) {
//            System.out.println(f.getSquare() + " " + f.getRoomCount());
//        }

        //Office office = new Office();
        //OfficeFloor officeFloor =new OfficeFloor(4);
//        System.out.println(officeFloor.getTotalSquare());
//        officeFloor.getTotalSquare();
//        System.out.println(officeFloor.getTotalRoomCount());
//        Office[] o = officeFloor.getSpaces();
//        System.out.println(officeFloor.getTotalRoomCount());
//
//        Office offfffice = officeFloor.getSpace(0);
//        System.out.println(officeFloor.getSpace(0));
//        System.out.println(officeFloor.getTotalRoomCount());
//
//        officeFloor.setSpace(new Office(1,1),0);
//        System.out.println(officeFloor.getTotalRoomCount());

        OfficeFloor officeFloor = new OfficeFloor(0);

        officeFloor.addSpace(new Office(1, 1), 0);
        officeFloor.addSpace(new Office(2, 2), 1);
        officeFloor.addSpace(new Office(3, 3), 2);
        officeFloor.addSpace(new Office(4, 4), 3);
        //officeFloor.addSpace(new Office(100500,100500),3);

        //System.out.println(officeFloor.getSpace(3).getRoomCount());
//
//officeFloor.removeSpace(2);

        //System.out.println(officeFloor.getSpace(4).getRoomCount());

        //officeFloor.setSpace(new Office(),3);
//        System.out.println(officeFloor.getSpace(3).getSquare());
//        //officeFloor.print();
//        officeFloor.getSpace(0);
//        officeFloor.getSpace(1);
//        officeFloor.getSpace(2);
//        officeFloor.getSpace(3);

        OfficeFloor OfficeFloor1 = new OfficeFloor(new Office[]{new Office(11, 11), new Office(12, 12), new Office(13, 13)});
        OfficeFloor OfficeFloor2 = new OfficeFloor(new Office[]{new Office(21, 21), new Office(22, 22), new Office(23, 23)});
        OfficeFloor OfficeFloor3 = new OfficeFloor(new Office[]{new Office(31, 31), new Office(32, 32), new Office(33, 33)});

        //OfficeFloor1.removeSpace(0);
        OfficeBuilding officeBuilding = new OfficeBuilding(new OfficeFloor[]{OfficeFloor1, OfficeFloor2, OfficeFloor3});

        Space o1 = officeBuilding.getSpace(6);
        officeBuilding.setSpace(new Office(), 6);
        Space o2 = officeBuilding.getSpace(6);


        officeBuilding.addSpace(new Office(100500, 900500), 1);
        //Space o3 = officeBuilding.getSpace(9);


//
//        officeBuilding.removeSpace(6);
        Space o4 = officeBuilding.getSpace(1);
//
        officeBuilding.addSpace(new Flat(), 1);
//
//        Office o5 = officeBuilding.getBestSpace();

        Space[] o6 = officeBuilding.getSortSpaces();

        Space o7 = officeBuilding.getFloor(2).getSpace(0);
        officeFloor.getSpace(0);
//        try {
//            BufferedReader in = new BufferedReader(new FileReader("in.txt")); //InputStreamReader(System.in));
//            in.close();
//        }
//        catch(IOException e) {
//            System.out.println("Some error occurred!111");
//        }
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
//
//            out.close();
//        }
//        catch(IOException e) {
//            System.out.println("Some error occurred!222");
//        }

//        try (BufferedReader in = new BufferedReader()){
//            PrintWriter fileWriter =  new PrintWriter(new BufferedWriter(new FileWriter("txt.txt")));
//            Buildings.writeBuilding(officeBuilding, fileWriter);
//            Buildings.readBuilding(in);
//
//        } catch (FileNotFoundException e0) {
//            System.out.println("error1");
//        } catch (IOException e1) {
//            System.out.println("error2");
//        }

        try {

            FileReader in = new FileReader("work.txt");
            FileWriter outputStream = new FileWriter("D:/work.txt");

            //FileOutputStream fileOutputStream = new FileOutputStream("txt.txt");
            //FileInputStream fileInputStream = new FileInputStream("txt1.txt");

            //Buildings.outputBuilding(officeBuilding, fileOutputStream);
            //Buildings.inputBuilding(fileInputStream);

            //fileInputStream.close();
            //fileOutputStream.close();
           Buildings.writeBuilding(officeBuilding, outputStream);
            Buildings.readBuilding(in);

            in.close();
            outputStream.close();

        }catch (IOException e){
            System.out.println("error1");
        }
    }
}
