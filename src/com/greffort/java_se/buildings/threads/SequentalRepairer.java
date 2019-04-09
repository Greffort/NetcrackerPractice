package com.greffort.java_se.buildings.threads;

import com.greffort.java_se.interfaces.Floor;

/**
 * @author SequentalRepairer
 * <p>
 * Практика 7. Задание 2
 * <p>
 * В пакете com.greffort.buildings.threads создайте два новых класса SequentalRepairer и SequentalCleaner, реализующих интерфейс Runnable,
 * с инструкциями для нитей.
 * <p>
 * Нити должны обеспечивать поочередность операций ремонта-уборки помещений (т.е. в консоль сообщения выводятся в порядке
 * Repairing-Cleaning-Repairing-Cleaning-…) независимо от приоритетов потоков. Уборка помещения не может следовать до ремонта помещения.
 * <p>
 * Для этого потребуется описать вспомогательный класс семафора
 * а лучше семафора с очередью, объект которого будет использоваться при взаимодействии нитей.
 * <p>
 * <p>
 * В остальном новые реализации нити должны повторять функциональность нитей из задания 1.
 */
public class SequentalRepairer implements Runnable {

    Floor floor;

    MySemaphore semaphore;

    public SequentalRepairer(Floor floor, MySemaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < floor.getCountSpace(); i++) {
            semaphore.acquire(this);
            System.out.println("Repairer room number " + i + " with total area " + floor.getSpace(i).getSquare() + " square meters");
            semaphore.release();
        }


    }
}
