package com.greffort.buildings.threads;

import com.greffort.interfaces.Floor;

/*
Первая нить последовательно считывает значения площадей помещений этажа.

Каждый раз, когда она читает значение площади помещения, она выводит в консоль сообщение вида
«Repairing space number <index> with total area <area> square meters».

По достижении конца этажа, а также в случае прерывания нить заканчивает свое выполнение, выводя сообщение об этом.
*/
public class Repairer extends Thread {

    Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < floor.getCountSpace(); i++) {
            System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
            if (isInterrupted()) {
                System.out.println("Thread interrupted ");
                break;
            }
        }
    }
}
