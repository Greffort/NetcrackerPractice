package com.greffort.buildings.dwelling;

import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class DwellingFloor implements Floor, Serializable, Iterable<Space> {

    private Space[] arrayFlat;

    public DwellingFloor(int countFlat) {
        this.arrayFlat = new Flat[countFlat];
        for (int i = 0; i < arrayFlat.length; i++) {
            arrayFlat[i] = new Flat();
        }
    }

    public DwellingFloor(final Space[] arrayFlat) {
        this.arrayFlat = arrayFlat;
    }

    public int getCountSpace() {
        return arrayFlat.length;
    }

    public double getTotalSquare() {
        double totalSquare = 0;
        for (Space flat : arrayFlat) {
            totalSquare += flat.getSquare();
        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int RoomCount = 0;
        for (Space flat : arrayFlat) {
            RoomCount += flat.getRoomCount();
        }
        return RoomCount;
    }

    public Space[] getSpaces() {
        return arrayFlat;
    }

    public Space getSpace(final int index) {
        if (index < 0 || index > arrayFlat.length) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return this.arrayFlat[index];
    }

    public void setSpace(final Space space, final int index) {
        if (index < 0 || index > arrayFlat.length) {
            throw new SpaceIndexOutOfBoundsException();
        }
        this.arrayFlat[index] = space;
    }

    public void addSpace(final Space space, final int index) {
        if (index < 0 || index > arrayFlat.length + 1) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newArrayFlat;
        newArrayFlat = new Space[arrayFlat.length + 1];
        for (int i = 0, j = 0; i < newArrayFlat.length; i++, j++) {
            if (j <= arrayFlat.length) {
                if (j == index) {
                    newArrayFlat[i] = space;
                    i++;
                } else {
                    newArrayFlat[i] = arrayFlat[j];
                }
                if (index < arrayFlat.length) {
                    newArrayFlat[i] = arrayFlat[j];
                }
            }
        }
        this.arrayFlat = newArrayFlat;
    }

    public void removeSpace(final int index) {
        if (index < 0 || index > arrayFlat.length) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newArrayFlat = new Flat[arrayFlat.length - 1];
        for (int i = 0, j = 0; i < arrayFlat.length - 1; i++, j++) {
            if (i == index) {
                j++;
            }
            newArrayFlat[i] = arrayFlat[j];
        }
        this.arrayFlat = newArrayFlat;
    }

    public Space getBestSpace() {
        double bestSpace = 0;
        int index = 0;
        for (int i = 0; i < arrayFlat.length; i++) {
            if (arrayFlat[i].getSquare() > bestSpace) {
                bestSpace = arrayFlat[i].getSquare();
                index = i;
            }
        }
        return arrayFlat[index];
    }

    public String toString() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DwellingFloor (" + getCountSpace() + ", ");
        for (int i = 0; i < arrayFlat.length; i++) {
            stringBuffer.append(arrayFlat[i].toString() + "), ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwellingFloor that = (DwellingFloor) o;
        return Arrays.equals(arrayFlat, that.arrayFlat);
    }

    @Override
    public int hashCode() {
        int p = 17;
        int result = 0;
        for (int i = 0; i < getCountSpace(); i++) {
            result += getCountSpace() * p | getSpace(i).hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Space[] spaces = new Space[getCountSpace()];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = (Space) getSpace(i).clone();
        }
        return new DwellingFloor(spaces);
    }

    public Iterator<Space> iterator() {
        return new Iterator<>();
    }

    private class Iterator<E> implements java.util.Iterator<E>, Serializable {

        private int index = 0;

        public boolean hasNext() {
            if (arrayFlat == null) {
                return false;
            } else {
                return index < arrayFlat.length;
            }
        }

        public E next() throws NoSuchElementException {
            return (E) arrayFlat[index++];
        }

    }

    public int compareTo(Floor floor) {
        /**
         * В классах этажей реализуйте метод int compareTo(T o) таким образом,
         * чтобы он сравнивал объекты этажей по количеству помещений и считал бОльшим этаж с бОльшим количеством помещений
         *
         * Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед тем, который передается
         * через параметр.
         * Если метод вернет положительное число, то, наоборот, после второго объекта.
         * Если метод возвратит ноль, значит, оба объекта равны.
         */
        if (this.getCountSpace() > floor.getCountSpace()) {
            return 1;
        }
        if (this.getCountSpace() < floor.getCountSpace()) {
            return -1;
        } else {
            return 0;
        }
    }
}
