package com.greffort.dwelling;

import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public final class DwellingFloor implements Floor {
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

    public Space[] getArrayFloors() {
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
}
