package com.greffort.dwelling;

import com.greffort.exception.*;
import com.greffort.interfaces.*;
import org.jetbrains.annotations.NotNull;

public final class Dwelling implements Building {
////    +  Создайте публичный класс Dwelling жилого здания, основанный на массиве этажей здания.
////    + Номер квартиры явно не хранится.
////    + Нумерация квартир в доме сквозная и начинается с нуля.
////    ? Конструктор может принимать количество этажей и массив количества квартир по этажам.
////    + Конструктор может принимать массив этажей дома.
////    + Создайте метод получения общего количества этажей дома.
////    + Создайте метод получения общего количества квартир дома.
////    + Создайте метод получения общей площади квартир дома.
////    + Создайте метод получения общего количества комнат дома.
////    + Создайте метод получения массива этажей жилого дома.
////
////    + Создайте метод получения объекта этажа, по его номеру в доме.
////    + Создайте метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
////    + Создайте метод получения объекта квартиры по ее номеру в доме.
////    + Создайте метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры.
////    + Создайте метод добавления квартиры в дом по будущему номеру квартиры в доме (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры (количество этажей в доме при этом не увеличивается).
////    + Создайте метод удаления квартиры по ее номеру в доме.
////    + Создайте метод getBestSpace() получения самой большой по площади квартиры дома.
////    Создайте метод получения отсортированного по убыванию площадей массива квартир.
////
////    Объедините имеющиеся на данный момент типы в пакет com.greffort.dwelling.
////    Проверьте работу классов, создав экземпляр многоэтажного жилого дома с различными произвольными
////    значениями количества квартир по этажам, а также площадей и количества комнат в квартирах.

    private Floor[] dwellingFloors;

    public Dwelling(final int dwellingFloorCount, @NotNull final int[] arrayFlatCounts) {
        this.dwellingFloors = new DwellingFloor[dwellingFloorCount];
        for (int i = 0; i < arrayFlatCounts.length; i++) {
            new DwellingFloor(arrayFlatCounts[i]);
        }
    }

    public Dwelling(final Floor[] arrayDwellingFloor) {
        this.dwellingFloors = arrayDwellingFloor;
    }

    public int getCountFloors() {
        return dwellingFloors.length;
    }

    public int getCountSpaces() {
        int totalFlatCount = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            totalFlatCount += dwellingFloors[i].getCountSpace();
        }
        return totalFlatCount;
    }

    public double getTotalSquare() {
        double totalSquare = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            totalSquare += dwellingFloors[i].getTotalSquare();
        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int totalRoomCount = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            totalRoomCount += dwellingFloors[i].getTotalRoomCount();
        }
        return totalRoomCount;
    }

    public Floor[] getFloors() {
        return dwellingFloors;
    }

    public Floor getFloor(final int index) {
        if (index < 0 || index > dwellingFloors.length) {
            throw new FloorIndexOutOfBoundsException();
        }
        if (index >= 0 && index <= dwellingFloors.length) {
            return dwellingFloors[index];
        } else {
            System.out.println("There is no such floor!");
            return null;
        }
    }

    public void setFloor(final Floor dwellingFloor, final int index) {
        if (index < 0 || index > dwellingFloors.length) {
            throw new FloorIndexOutOfBoundsException();
        }
        if (index >= 0 && index <= dwellingFloors.length) {
            this.dwellingFloors[index] = dwellingFloor;
        } else {
            System.out.print("Такого этажа нет");
        }
    }

    public Space getSpace(final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayFloors().length; j++) {
                if (index == counter) {
                    return dwellingFloors[i].getSpace(j);
                } else {
                    counter++;
                }
            }
        }
        return null;
    }

    public void setSpace(final Space flat, final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayFloors().length; j++) {
                if (index == counter) {
                    dwellingFloors[i].setSpace(flat, index);
                } else {
                    counter++;
                }
            }
        }
    }

    public void addSpace(final Space flat, final int index) {
        if (index < 0 || index > getCountSpaces() + 1) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 1;
        int sumArrayLenght = 0;
        outterLoop:
        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayFloors().length; j++) {
                if (index == counter) {
                    dwellingFloors[i].addSpace(flat, index - sumArrayLenght);
                }
                if (index == 0) {
                    dwellingFloors[i].addSpace(flat, index);
                    break outterLoop;
                }
                counter++;
            }
            sumArrayLenght += dwellingFloors[i].getCountSpace();
        }
    }

    public void removeSpace(final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 1;
        int sumArrayLenght = 0;
        outterLoop:
        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayFloors().length; j++) {
                if (index == counter) {
                    dwellingFloors[i].removeSpace(index - sumArrayLenght);
                }
                if (index == 0) {
                    dwellingFloors[i].removeSpace(index);
                    break outterLoop;
                }
                counter++;
            }
            sumArrayLenght += dwellingFloors[i].getCountSpace();
        }
    }

    public Space getBestSpace() {
        double bestSpace = 0;
        int index = 0;
        Space flat = null;

        for (int i = 0; i < dwellingFloors.length; i++) {
            if (dwellingFloors[i].getBestSpace().getSquare() > bestSpace) {
                flat = dwellingFloors[i].getBestSpace();
            }
        }
        return flat;
    }

    public Space[] getSortSpaces() {
        int sumArrayLenght = 0;
        Space[] allFlats;
        for (int i = 0; i < dwellingFloors.length; i++) {
            sumArrayLenght += dwellingFloors[i].getArrayFloors().length;
        }
        int index = 0;
        allFlats = new Flat[sumArrayLenght];
        for (int i = 0; i < dwellingFloors.length; i++) {
            Space[] flatsl = dwellingFloors[i].getArrayFloors();
            for (int j = 0; j < flatsl.length; j++) {
                allFlats[index] = flatsl[j];
                index++;
            }
        }
        for (int i = allFlats.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (allFlats[j].getSquare() < allFlats[j + 1].getSquare()) {
                    Space tmp = allFlats[j];
                    allFlats[j] = allFlats[j + 1];
                    allFlats[j + 1] = tmp;
                }
            }
        }
        return allFlats;
    }


}
