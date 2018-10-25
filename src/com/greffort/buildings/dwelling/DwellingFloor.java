package com.greffort.buildings.dwelling;

import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;

public final class DwellingFloor implements Floor, Serializable {
//
//    + Создайте публичный класс DwellingFloor этажа жилого здания, основанный на массиве квартир.
//    Номер квартиры явно не хранится.
//    Нумерация квартир на этаже сквозная и начинается с нуля.
//    + Конструктор может принимать количество квартир на этаже.
//    + Конструктор может принимать массив квартир этажа.
//    + Создайте метод получения количества квартир на этаже.
//    + Создайте метод получения общей площади квартир этажа.
//    + Создайте метод получения общего количества комнат этажа.
//
//    + Создайте метод получения массива квартир этажа.
//    + Создайте метод получения объекта квартиры по ее номеру на этаже.
//    + Создайте метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
//    + Создайте метод добавления новой квартиры на этаже по будущему номеру квартиры
//    + (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры.
//       Можно ли указать 100 индекс?
//    + Создайте метод удаления квартиры по ее номеру на этаже.
//    + Создайте метод getBestSpace() получения самой большой по площади квартиры этажа.

    private Space[] arrayFlat;


    public DwellingFloor(final int countFlat) {
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

    /**
     * Добавьте в классы этажей DwellingFloor, OfficeFloor реализации метода String toString().
     * Методы выводят тип этажа, текущее количество помещений этажа и соответствующую информацию по каждому помещению,
     * используя метод toString() помещения. Например,
     * DwellingFloor (3, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0))
     */
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
            result += getCountSpace()*p | getSpace(i).hashCode();
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
