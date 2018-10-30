package com.greffort.buildings.dwelling;

import com.greffort.exception.*;
import com.greffort.interfaces.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dwelling implements Building, Serializable {

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
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
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
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
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
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
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
            for (int j = 0; j < dwellingFloors[i].getSpaces().length; j++) {
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
            sumArrayLenght += dwellingFloors[i].getSpaces().length;
        }
        int index = 0;
        allFlats = new Space[sumArrayLenght];
        for (int i = 0; i < dwellingFloors.length; i++) {
            Space[] flatsl = dwellingFloors[i].getSpaces();
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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Dwelling (" + getCountFloors() + ", ");

        for (int i = 0; i < dwellingFloors.length; i++) {
            stringBuffer.append(dwellingFloors[i].toString() + ", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dwelling dwelling = (Dwelling) o;
        return Arrays.equals(dwellingFloors, dwelling.dwellingFloors);
    }

    @Override
    public int hashCode() {

/**
 * Добавьте в классы зданий реализации методов int hashCode().
 * Значение хеш-функции здания вычисляется как значение побитового исключающего
 * ИЛИ количества этажей здания и значений хеш-функций этажей здания.
 */
        int p = 17;
        int result = 0;
        for (int i = 0; i < getCountFloors(); i++) {
            result += getCountFloors() * p | getFloor(i).hashCode();
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Floor[] floors = new Floor[getCountFloors()];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = (Floor) getSpace(i).clone();
        }
        return new Dwelling(floors);
    }

    public Iterator<Floor> iterator(){
        return new Iterator<>();
    }

    private class Iterator<E> implements java.util.Iterator<E> {

        private int index = 0;

        public boolean hasNext() {
            if(dwellingFloors == null){
                return false;
            }else{
            return index<dwellingFloors.length;}
        }

        public E next() throws NoSuchElementException {
            return (E)dwellingFloors[index++];
        }

    }
}
