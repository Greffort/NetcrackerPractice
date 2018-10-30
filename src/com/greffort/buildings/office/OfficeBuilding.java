package com.greffort.buildings.office;

import com.greffort.exception.*;
import com.greffort.interfaces.*;
import com.greffort.linkedList.DoubleLinkedList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class OfficeBuilding implements Building, Serializable {

    private DoubleLinkedList<Floor> officeFloorDoubleLinkedList;

    public OfficeBuilding(final int numberOfficeFloors, final int[] numberOfficeFloor) {
        officeFloorDoubleLinkedList = new DoubleLinkedList<Floor>();
        for (int i = 0; i < numberOfficeFloors; i++) {
            addNode(new OfficeFloor(numberOfficeFloor[i]), i);
        }
    }

    public OfficeBuilding(final Floor[] officeFloors) {
        officeFloorDoubleLinkedList = new DoubleLinkedList<Floor>();
        for (int i = 0; i < officeFloors.length; i++) {
            addNode(officeFloors[i], i);
        }
    }

    public OfficeBuilding(final DoubleLinkedList<Floor> doubleLinkedList) {
        this.officeFloorDoubleLinkedList = doubleLinkedList;
    }

    @Contract(pure = true)
    private Floor getNode(final int index) {
        return officeFloorDoubleLinkedList.getNode(index);
    }

    private void addNode(final Floor officeFloor, final int index) {
        officeFloorDoubleLinkedList.addNode(officeFloor, index);
    }

    private void removeNode(final int index) throws ArrayStoreException, RuntimeException {
        officeFloorDoubleLinkedList.removeNode(index);
        if (false) throw new ArrayStoreException();
    }

    @Contract(pure = true)
    public int getCountFloors() {
        return officeFloorDoubleLinkedList.getSize();
    }

    public int getCountSpaces() {
        int countSpaces = 0;
        for (Floor floor : this) {
            countSpaces += floor.getSpaces().length;
        }
//        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
//            countSpaces += getNode(i).getSpaces().length;
//        }
        return countSpaces;
    }

    public double getTotalSquare() {
        int totalSquare = 0;
        for (Floor floor : this) {
            totalSquare += floor.getTotalSquare();
        }
//        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
//            totalSquare += getNode(i).getTotalSquare();
//        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int totalRoomCount = 0;
        for (Floor floor : this) {
            totalRoomCount += floor.getTotalRoomCount();
        }
//        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
//            totalRoomCount += getNode(i).getTotalRoomCount();
//        }
        return totalRoomCount;
    }

    public Floor[] getFloors() {
        Floor[] floors = new Floor[officeFloorDoubleLinkedList.getSize()];
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            floors[i] = officeFloorDoubleLinkedList.getNode(i);
        }
        return floors;
    }

    public Floor getFloor(final int index) {
        if (index < 0 || index > officeFloorDoubleLinkedList.getSize()) {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNode(index);
    }

    public void setFloor(final Floor officeFloor, final int index) {
        if (index < 0 || index > officeFloorDoubleLinkedList.getSize()) {
            throw new FloorIndexOutOfBoundsException();
        }
        officeFloorDoubleLinkedList.setNode(officeFloor, index);
    }

    public Space getSpace(final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (Floor floor : this) {
            for (Space space : floor) {
                if (index == counter) {
                    return space;
                } else {
                    counter++;
                }
            }
        }
//        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
//            for (int j = 0; j < officeFloorDoubleLinkedList.getNode(i).getCountSpace(); j++) {
//                if (index == counter) {
//                    return officeFloorDoubleLinkedList.getNode(i).getSpace(j);
//                } else {
//                    counter++;
//                }
//            }
//        }
        return null;
    }

    public void setSpace(final Space office, final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (Floor floor : this) {
            for (Space space : floor) {
                if (index == counter) {
                    space = office;
                    return;
                } else {
                    counter++;
                }
            }
        }
//        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
//            for (int j = 0; j < officeFloorDoubleLinkedList.getNode(i).getCountSpace(); j++) {
//                if (index == counter) {
//                    officeFloorDoubleLinkedList.getNode(i).setSpace(office, j);
//                    return;
//                } else {
//                    counter++;
//                }
//            }
//        }
    }

    public void addSpace(final Space office, final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (Floor floor : this) {
            floor.addSpace(office, index);
        }

        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoubleLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    officeFloorDoubleLinkedList.getNode(i).addSpace(office, j);
                    return;
                } else {
                    counter++;
                }
            }
        }
    }

    public void removeSpace(final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoubleLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    officeFloorDoubleLinkedList.getNode(i).removeSpace(j);
                    return;
                } else {
                    counter++;
                }
            }
        }
    }

    public Space getBestSpace() {
        double bestSpace = 0;
        int index = 0;
        Space office = null;

        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            if (officeFloorDoubleLinkedList.getNode(i).getBestSpace().getSquare() > bestSpace) {
                office = officeFloorDoubleLinkedList.getNode(i).getBestSpace();
            }
        }
        return office;
    }

    public Space[] getSortSpaces() {
        int sumArrayLenght = 0;
        Space[] allOffice;
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            sumArrayLenght += officeFloorDoubleLinkedList.getNode(i).getCountSpace();
        }
        int index = 0;
        allOffice = new Space[sumArrayLenght];
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            Space[] offices = officeFloorDoubleLinkedList.getNode(i).getSpaces();
            for (int j = 0; j < offices.length; j++) {
                allOffice[index] = offices[j];
                index++;
            }
        }
        for (int i = allOffice.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (allOffice[j].getSquare() < allOffice[j + 1].getSquare()) {
                    Space tmp = allOffice[j];
                    allOffice[j] = allOffice[j + 1];
                    allOffice[j + 1] = tmp;
                }
            }
        }
        return allOffice;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OfficeBuilding (" + getCountFloors() + ", ");
        for (int i = 0; i < getCountFloors(); i++) {
            stringBuffer.append(getFloor(i).toString() + ", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeBuilding that = (OfficeBuilding) o;
        boolean b = false;
        for (int i = 0; i < getCountFloors(); i++) {
            if (this.getFloor(i).equals(that.getFloor(i))) {
                b = true;
            } else {
                b = false;
                break;
            }
        }
        return b;
    }

    @Override
    public int hashCode() {
        int p = 17;
        int result = 0;
        for (int i = 0; i < getCountFloors(); i++) {
            result += getCountFloors() * p | getFloor(i).hashCode();
        }
        return result;
    }


    @NotNull
    @Contract(" -> new")
    @Override
    public Object clone() throws CloneNotSupportedException {
        Floor[] floors = new Floor[getCountFloors()];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = (Floor) getSpace(i).clone();
        }
        return new OfficeBuilding(floors);
    }

    public Iterator<Floor> iterator() {
        return officeFloorDoubleLinkedList.iterator();
        //return new Iterator<>();
    }

//    private class Iterator<E> implements java.util.Iterator<E> {
//
//        private int index = 0;
//
//        public boolean hasNext() {
//            if (officeFloorDoubleLinkedList == null) {
//                return false;
//            } else {
//                return index < officeFloorDoubleLinkedList.getSize();
//            }
//        }
//
//        public E next() throws NoSuchElementException {
//            return (E) officeFloorDoubleLinkedList.getNode(index++);
//        }
//
//    }
}
