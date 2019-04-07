package com.greffort.java_se.buildings.threads;

import com.greffort.java_se.interfaces.Floor;

 /*
Вторая нить последовательно считывает значения площадей помещений этажа.
Каждый раз, когда она читает значение помещения,
она выводит в консоль сообщение вида «Cleaning room number <index> with total area <area> square meters».
По достижении конца этажа, а также в случае прерывания нить заканчивает свое выполнение, выводя сообщение об этом.

В методе main () следует создать 3 участвующих в процессе объекта (две нити и объект этажа)
и запустить нити на выполнение.
Запустите программу несколько раз. Попробуйте варьировать приоритеты нитей.
Проверьте поведение нитей в случае их прерывания вызовом метода interrupt ().
 */

public class Cleaner extends Thread {

    Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
            for (int i = 0; i < floor.getCountSpace(); i++) {
                    System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
                if (isInterrupted()) {
                    System.out.println("Thread interrupted ");
                    break;
                }
            }
    }
}
