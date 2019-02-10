package com.greffort.main.main007;

import com.greffort.buildings.office.Office;
import com.greffort.buildings.office.OfficeFloor;
import com.greffort.buildings.threads.MySemaphore;
import com.greffort.buildings.threads.SequentalCleaner;
import com.greffort.buildings.threads.SequentalRepairer;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;

/**
 * @author Main0702
 * <p>
 * Класс Main для практики номер 7
 * <p>
 * Задание:
 * В методе main () следует создать 3 участвующих в процессе объекта (две нити и объект этажа)
 * и запустить нити на выполнение.
 * Запустите программу несколько раз. Попробуйте варьировать приоритеты нитей.
 * Проверьте поведение нитей в случае их прерывания вызовом метода interrupt ().
 */
public class Main0703 {

    /**
     *
     * @param args
     *
     * Добавьте в класс Buildings со статическими методами обработки реализацию метода
     * Floor synchronizedFloor (Floor floor),
     * возвращающего ссылку на оболочку указанного объекта этажа, безопасную с точки зрения многопоточности.
     * Для этого потребуется в пакете buildings описать новый класс декоратора SynchronizedFloor, реализующий
     * с обеспечением синхронизации методы интерфейса Floor, а также перегружающий ряд методов класса Object.
     * Создание специальных итераторов и их синхронизация не требуются
     */
    public static void main(String[] args) {

//        Floor spaces001 = new OfficeFloor(new Space[]{new Office(21,21),
//                new Office(22,22), new Office(23,23),
//                new Office(22,22), new Office(23,23),
//                new Office(22,22), new Office(23,23)});
//
//        Floor spaces002 = new OfficeFloor(new Space[]{new Office(21,21),
//                new Office(22,22), new Office(23,23),
//                new Office(22,22), new Office(23,23),
//                new Office(22,22), new Office(23,23)});
//
////        Repairer repairer = new Repairer(spaces001);
////        Cleaner cleaner = new Cleaner(spaces001);
//
////        repairer.setPriority(repairer.MAX_PRIORITY);
////        cleaner.setPriority(cleaner.MAX_PRIORITY);
//
////        repairer.start();
////        repairer.interrupt();
////        System.out.println(repairer.getState());
////        cleaner.start();
////        cleaner.interrupt();
//
//        MySemaphore s = new MySemaphore();
//
//        Runnable r = new SequentalRepairer(spaces001, s);
//        Runnable c = new SequentalCleaner(spaces002, s);
//
//
//        Thread t1 = new Thread(r);
//        Thread t2 = new Thread(c);
//
////        t1.setPriority(t1.MAX_PRIORITY);
//        t2.start();
//        t1.start();
    }
}
