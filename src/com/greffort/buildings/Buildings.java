package com.greffort.buildings;

import com.greffort.interfaces.Building;

import java.io.*;

public final class Buildings {

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

    public static void outputBuilding(Building building, OutputStream out) {

    }

    public static Building inputBuilding(InputStream in) {
        return null;
    }

    public static void writeBuilding(Building building, Writer out) {

    }

    public static Building readBuilding(Reader in) {

        return null;
    }
}
