package com.greffort.buildings;

import com.greffort.building.OfficeBuilding;
import com.greffort.factory.BuildingFactory;
import com.greffort.factory.Factory;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;

import java.io.*;


public  class Buildings {
/**
//      Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building,
//      содержащий статические методы ввода-вывода зданий:

//          •	записи данных о здании в байтовый поток
//      public static void outputBuilding (Building building, OutputStream out);

//          •	чтения данных о здании из байтового потока
//      public static Building inputBuilding (InputStream in);

//          •	записи здания в символьный поток
//      public static void writeBuilding (Building building, Writer out);

//          •	чтения здания из символьного потока
//      public static Building readBuilding (Reader in).

//      Записанные данные о здании представляет собой последовательность чисел, первым из которых является количество этажей,
//      далее следует количество помещений текущего этажа и соответствующие значения количества комнат и площадей помещений текущего этажа.

//      Например, символьная запись для трехэтажного здания:
//          «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»

 //      Для чтения этажа из символьного потока можно использовать StreamTokenizer.
//      Проверьте возможности всех реализованных методов, в качестве реальных потоков используя файловые потоки,
//      а также потоки System.in и System.out.

---------------------------------------------------------
    Добавьте в класс Buildings следующие методы:
            •	сериализации здания в байтовый поток
    public static void serializeBuilding (Building building, OutputStream out);
            •	десериализации здания из байтового потока
    public static Building deserializeBuilding (InputStream in);
*/
    private static Factory factory = new BuildingFactory(); // сделать выбор фабрики
    public  static  void setFactory (Factory factory){
        this.factory = factory;
    }
    public static Building createBuilding(Floor[] floors){
        factory.createBuilding(floors);
    }


    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        // Сделать запись первым байтом - тип здания, тип этажа, тип помещения
        //write count floor on building
        dataOutputStream.write(building.getCountFloors());
        for (int i = 0, countFloors = building.getCountFloors(); i < countFloors; i++) {
            //write count spaces on floor
            dataOutputStream.write(building.getFloors()[i].getCountSpace());
            for (int j = 0, countSpaces = building.getFloors()[i].getCountSpace(); j < countSpaces; j++) {
                //write count room
                dataOutputStream.write(building.getFloors()[i].getSpaces()[j].getRoomCount());
                //write space
                dataOutputStream.writeDouble(building.getFloors()[i].getSpaces()[j].getSquare());
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        //Factory factory = new BuildingFactory();// сделать выбор фабрики
        //count Floors
        Floor[] floors = new Floor[dataInputStream.readInt()];
        for (int i = 0; i < floors.length; i++) {
            //count Spaces on Floor
            Space[] spaces = new Space[dataInputStream.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = factory.createSpace(dataInputStream.readDouble(), dataInputStream.readInt());
            }
            floors[i] = factory.createFloor(spaces);
        }
        return factory.createBuilding(floors);
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        //PrintWriter printWriter = new PrintWriter(out);
        // Сделать запись первым байтом - тип здания, добавить поле в классы
        out.write(building.getCountFloors() + " ");
        for (int i = 0, countFloors = building.getCountFloors(); i < countFloors; i++) {
            out.write(building.getFloors()[i].getCountSpace() + " ");
            for (int j = 0, countSpaces = building.getFloors()[i].getCountSpace(); j < countSpaces; j++) {
                out.write(building.getFloors()[i].getSpaces()[j].getRoomCount() + " ");
                out.write(building.getFloors()[i].getSpaces()[j].getSquare() + " ");
            }
        }
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);

//        Factory factory = new BuildingFactory(); // сделать выбор фабрики

        Floor[] floors = new Floor[(int) streamTokenizer.nextToken()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[(int) streamTokenizer.nextToken()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j] = factory.createSpace((double) streamTokenizer.nextToken(), (int) streamTokenizer.nextToken());
            }
            floors[i] = factory.createFloor(spaces);
        }
        return factory.createBuilding(floors);
    }

    public static void serializeBuilding (Building building, OutputStream out){

    }
    public static Building deserializeBuilding (InputStream in){

        return null;
    }
}
