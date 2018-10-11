package com.greffort.building;

import com.greffort.exception.*;
import com.greffort.interfaces.*;
import com.greffort.linkedList.DoubleLinkedList;

public final class OfficeBuilding implements Building {

// + Создайте класс OfficeBuilding офисного здания.
//    + Работа класса должна быть основана на двусвязном циклическом списке этажей с выделенной головой.
//    + Номер офиса явно не хранится.
//    + Нумерация офисов в здании сквозная и начинается с нуля.
//    Создайте три вспомогательных приватных метода:
//    + - приватный метод получения узла по его номеру;
//    + - приватный метод добавления узла в список по номеру;
//    + - приватный метод удаления узла из списка по его номеру.
//    + Конструктор может принимать количество этажей и массив количества офисов по этажам.
//    + Конструктор может принимать массив этажей офисного здания.
//    + Создайте метод получения общего количества этажей здания.
//    + Создайте метод получения общего количества офисов здания.
//    + Создайте метод получения общей площади помещений здания.
//    + Создайте метод получения общего количества комнат здания.
//    + Создайте метод получения массива этажей офисного здания.
//    + Создайте метод получения объекта этажа, по его номеру в здании.
//    + Создайте метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
//    + Создайте метод получения объекта офиса по его номеру в офисном здании.
//    + Создайте метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
//    + Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
//    + Создайте метод удаления офиса по его номеру в здании.
//    + Создайте метод getBestSpace() получения самого большого по площади офиса здания.
//    + Создайте метод получения отсортированного по убыванию площадей массива офисов.

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

    public int getCountFloors() {
        return officeFloorDoubleLinkedList.getSize();
    }

    public int getCountSpaces() {
        int countSpaces = 0;
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            countSpaces += getNode(i).getArrayFloors().length;
        }
        return countSpaces;
    }

    public double getTotalSquare() {
        int totalSquare = 0;
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            totalSquare += getNode(i).getTotalSquare();
        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int totalRoomCount = 0;
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            totalRoomCount += getNode(i).getTotalRoomCount();
        }
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
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoubleLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    return officeFloorDoubleLinkedList.getNode(i).getSpace(j);
                } else {
                    counter++;
                }
            }
        }
        return null;
    }

    public void setSpace(final Space office, final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < officeFloorDoubleLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoubleLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    officeFloorDoubleLinkedList.getNode(i).setSpace(office, j);
                    return;
                } else {
                    counter++;
                }
            }
        }
    }

    public void addSpace(final Space office, final int index) {
        if (index < 0 || index > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
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
            Space[] offices = officeFloorDoubleLinkedList.getNode(i).getArrayFloors();
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


}
