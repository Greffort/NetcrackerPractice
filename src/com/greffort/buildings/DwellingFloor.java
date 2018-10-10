package com.greffort.buildings;

import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.IFloor;
import com.greffort.interfaces.ISpace;

public final class DwellingFloor implements IFloor {
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

    private ISpace[] arrayFlat;

    public DwellingFloor(final int countFlat) {
        this.arrayFlat = new Flat[countFlat];
        for (int i = 0; i < arrayFlat.length; i++) {
            arrayFlat[i] = new Flat();
        }
    }

    public DwellingFloor(final Flat[] arrayFlat) {
        this.arrayFlat = arrayFlat;
    }

    public int getCountSpace() {
        return arrayFlat.length;
    }

    public double getTotalSquare() {
        double totalSquare = 0;
        for (ISpace flat : arrayFlat) {
            totalSquare += flat.getSquare();
        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int RoomCount = 0;
        for (ISpace flat : arrayFlat) {
            RoomCount += flat.getRoomCount();
        }
        return RoomCount;
    }

    public ISpace[] getArrayFloors() {
        return arrayFlat;
    }

    public ISpace getSpace(final int index) {
        if (index < 0 || index > arrayFlat.length) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return this.arrayFlat[index];
    }

    public void setSpace(final ISpace iSpace, final int index) {
        if (index < 0 || index > arrayFlat.length) {
            throw new SpaceIndexOutOfBoundsException();
        }
        this.arrayFlat[index] = iSpace;
    }

    public void addSpace(final ISpace iSpace, final int index) {
        if (index < 0 || index > arrayFlat.length + 1) {
            throw new SpaceIndexOutOfBoundsException();
        }
        ISpace[] newArrayFlat;
        newArrayFlat = new Flat[arrayFlat.length + 1];
        for (int i = 0, j = 0; i < newArrayFlat.length; i++, j++) {
            if (j <= arrayFlat.length) {
                if (j == index) {
                    newArrayFlat[i] = iSpace;
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
        ISpace[] newArrayFlat = new Flat[arrayFlat.length - 1];
        for (int i = 0, j = 0; i < arrayFlat.length - 1; i++, j++) {
            if (i == index) {
                j++;
            }
            newArrayFlat[i] = arrayFlat[j];
        }
        this.arrayFlat = newArrayFlat;
    }

    public ISpace getBestSpace() {
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
