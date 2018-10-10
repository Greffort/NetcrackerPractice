package com.greffort.buildingsOffice;

import com.greffort.exception.*;
import com.greffort.interfaces.*;
import com.greffort.linkedList.DoublyLinkedList;


public final class OfficeBuilding implements IBuilding {

//    + Создайте класс OfficeBuilding офисного здания.
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

    private DoublyLinkedList<IFloor> officeFloorDoublyLinkedList;

    public OfficeBuilding(final int numberOfficeFloors, final int[] numberOfficeFloor) {
        officeFloorDoublyLinkedList = new DoublyLinkedList<IFloor>();
        for (int i = 0; i < numberOfficeFloors; i++) {
            addNode(new OfficeFloor(numberOfficeFloor[i]), i);
        }
    }

    public OfficeBuilding(final OfficeFloor[] officeFloors) {
        officeFloorDoublyLinkedList = new DoublyLinkedList<IFloor>();
        for (int i = 0; i < officeFloors.length; i++) {
            addNode(officeFloors[i], i);
        }
    }

    public OfficeBuilding(final DoublyLinkedList<IFloor> doublyLinkedList) {
        this.officeFloorDoublyLinkedList = doublyLinkedList;
    }

    private IFloor getNode(final int index) {
        return officeFloorDoublyLinkedList.getNode(index);
    }

    private void addNode(final OfficeFloor officeFloor, final int index) {
        officeFloorDoublyLinkedList.addNode(officeFloor, index);
    }

    private void removeNode(final int index) throws ArrayStoreException, RuntimeException {
        officeFloorDoublyLinkedList.removeNode(index);
        if (false) throw new ArrayStoreException();
    }

    public int getNumberFloors() {
        return officeFloorDoublyLinkedList.getSize();
    }

    public int getNumberSpaces() {
        int sum = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            sum += getNode(i).getArrayFloors().length;
        }
        return sum;
    }

    public double getTotalSquare() {
        int totalSquare = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            totalSquare += getNode(i).getTotalSquare();
        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int totalRoomCount = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            totalRoomCount += getNode(i).getTotalRoomCount();
        }
        return totalRoomCount;
    }

    public IFloor[] getFloors() {
        IFloor[] floors = new IFloor[officeFloorDoublyLinkedList.getSize()];
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            floors[i] = officeFloorDoublyLinkedList.getNode(i);
        }
        return floors;
    }

    public IFloor getFloor(final int index) {
        if (index < 0 || index > officeFloorDoublyLinkedList.getSize()) {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNode(index);
    }

    public void setFloor(final IFloor officeFloor, final int index) {
        if (index < 0 || index > officeFloorDoublyLinkedList.getSize()) {
            throw new FloorIndexOutOfBoundsException();
        }
        officeFloorDoublyLinkedList.setNode(officeFloor, index);
    }

    public ISpace getSpace(final int index) {
        if (index < 0 || index > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoublyLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    return officeFloorDoublyLinkedList.getNode(i).getSpace(j);
                } else {
                    counter++;
                }
            }
        }
        return null;
    }

    public void setSpace(final ISpace office, final int index) {
        if (index < 0 || index > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoublyLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    officeFloorDoublyLinkedList.getNode(i).setSpace(office, j);
                    return;
                } else {
                    counter++;
                }
            }
        }
    }

    public void addSpace(final ISpace office, final int index) {
        if (index < 0 || index > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoublyLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    officeFloorDoublyLinkedList.getNode(i).addSpace(office, j);
                    return;
                } else {
                    counter++;
                }
            }
        }
    }

    public void removeSpace(final int index) {
        if (index < 0 || index > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int counter = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            for (int j = 0; j < officeFloorDoublyLinkedList.getNode(i).getCountSpace(); j++) {
                if (index == counter) {
                    officeFloorDoublyLinkedList.getNode(i).removeSpace(j);
                    return;
                } else {
                    counter++;
                }
            }
        }
    }

    public ISpace getBestSpace() {
        double bestSpace = 0;
        int index = 0;
        ISpace office = null;

        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            if (officeFloorDoublyLinkedList.getNode(i).getBestSpace().getSquare() > bestSpace) {
                office = officeFloorDoublyLinkedList.getNode(i).getBestSpace();
            }
        }
        return office;
    }

    public ISpace[] getSortSpaces() {
        int sumArrayLenght = 0;
        ISpace[] allOffice;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            sumArrayLenght += officeFloorDoublyLinkedList.getNode(i).getCountSpace();
        }
        int index = 0;
        allOffice = new Office[sumArrayLenght];
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            ISpace[] offices = officeFloorDoublyLinkedList.getNode(i).getArrayFloors();
            for (int j = 0; j < offices.length; j++) {
                allOffice[index] = offices[j];
                index++;
            }
        }
        for (int i = allOffice.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (allOffice[j].getSquare() < allOffice[j + 1].getSquare()) {
                    ISpace tmp = allOffice[j];
                    allOffice[j] = allOffice[j + 1];
                    allOffice[j + 1] = tmp;
                }
            }
        }
        return allOffice;
    }

    private int getCountSpace() {
        int countOffice = 0;
        for (int i = 0; i < officeFloorDoublyLinkedList.getSize(); i++) {
            countOffice += officeFloorDoublyLinkedList.getNode(i).getCountSpace();
        }
        return countOffice;
    }
}
