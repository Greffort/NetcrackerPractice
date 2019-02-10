package com.greffort.buildings;

import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

/**
 * @author SynchronizedFloor
 * <p>
 * Для этого потребуется в пакете buildings описать новый класс декоратора SynchronizedFloor, реализующий
 * с обеспечением синхронизации методы интерфейса Floor, а также перегружающий ряд методов класса Object.
 * Создание специальных итераторов и их синхронизация не требуются
 */
public class SynchronizedFloor extends DecoratorFloor {

    public SynchronizedFloor(Floor floor) {
        super(floor);
    }

    @Override
    public synchronized int getCountSpace() {
        return super.getCountSpace();
    }

    @Override
    public synchronized double getTotalSquare() {
        return super.getTotalSquare();
    }

    @Override
    public synchronized int getTotalRoomCount() {
        return super.getTotalRoomCount();
    }

    @Override
    public synchronized Space[] getSpaces() {
        return super.getSpaces();
    }

    @Override
    public Space getSpace(int index) {
        return super.getSpace(index);
    }

    @Override
    public synchronized void setSpace(Space iSpace, int index) {
        super.setSpace(iSpace, index);
    }

    @Override
    public synchronized void addSpace(Space iSpace, int index) {
        super.addSpace(iSpace, index);
    }

    @Override
    public synchronized void removeSpace(int index) {
        super.removeSpace(index);
    }

    @Override
    public synchronized Space getBestSpace() {
        return super.getBestSpace();
    }

    @Override
    public synchronized int compareTo(@NotNull Floor o) {
        return super.compareTo(o);
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }
}
