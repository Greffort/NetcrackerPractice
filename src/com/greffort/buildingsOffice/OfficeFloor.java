package com.greffort.buildingsOffice;

import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.IFloor;
import com.greffort.interfaces.ISpace;
import com.greffort.linkedList.SingleLinkedList;
//import com.greffort.linkedList.DoubleLinkedList;

public final class OfficeFloor implements IFloor {

//    + Создайте класс OfficeFloor этажа офисного здания.
//    + Работа класса должна быть основана на односвязном циклическом списке офисов с выделенной головой.
//    + Номер офиса явно не хранится.
//
//    + Создайте приватный метод получения узла по его номеру.
//    + Создайте приватный метод добавления узла в список по номеру.
//    + Создайте приватный метод удаления узла из списка по его номеру.
//
//    + Конструктор может принимать количество офисов на этаже.
//    + Конструктор может принимать массив офисов этажа.
//    + Создайте метод получения количества офисов на этаже.
//    + Создайте метод получения общей площади помещений этажа.
//    + Создайте метод получения общего количества комнат этажа.
//    + Создайте метод получения массива офисов этажа.
//
//    + Создайте метод получения офиса по его номеру на этаже.
//    + Создайте метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
//    + Создайте метод добавления нового офиса на этаже по будущему номеру офиса.
//    + Создайте метод удаления офиса по его номеру на этаже.
//    + Создайте метод getBestSpace() получения самого большого по площади офиса этажа.
//private DoubleLinkedList<Office> officeSingleLinkedList = new DoubleLinkedList<>();

    private SingleLinkedList<ISpace> officeSingleLinkedList;

    public OfficeFloor(final int numberOffices) {
        officeSingleLinkedList = new SingleLinkedList<ISpace>();
        for (int i = 0; i < numberOffices; i++) {
            addNode(new Office(), i);
        }
    }

    public OfficeFloor(final SingleLinkedList<ISpace> officeSingleLinkedList) {
        this.officeSingleLinkedList = officeSingleLinkedList;
    }

    public OfficeFloor(Office[] offices) {
        this.officeSingleLinkedList = new SingleLinkedList<ISpace>();
        for (int i = 0; i < offices.length; i++) {
            addNode(offices[i], i);
        }
    }

    private void addNode(final ISpace iSpace, final int index) {
        officeSingleLinkedList.addNode(iSpace, index);//Exception in thread "main" java.lang.NullPointerException
    }

    private ISpace getNode(final int index) {
        return officeSingleLinkedList.getNode(index);
    }

    private void removeNode(final int index) {
        officeSingleLinkedList.removeNode(index);
    }

    public int getCountSpace() {
        return officeSingleLinkedList.getSize();
    }

    public double getTotalSquare() {
        double totalSquare = 0;
        for (int i = 0; i < officeSingleLinkedList.getSize(); i++) {
            totalSquare += officeSingleLinkedList.getNode(i).getSquare();
        }
        return totalSquare;
    }

    public int getTotalRoomCount() {
        int totalRoomCount = 0;
        for (int i = 0; i < officeSingleLinkedList.getSize(); i++) {
            totalRoomCount += officeSingleLinkedList.getNode(i).getRoomCount();
        }
        return totalRoomCount;
    }

    public ISpace[] getArrayFloors() {
        ISpace[] offices = new ISpace[officeSingleLinkedList.getSize()];
        for (int i = 0; i < officeSingleLinkedList.getSize(); i++) {
            offices[i] = officeSingleLinkedList.getNode(i);
        }
        return offices;
    }

    public ISpace getSpace(final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return getNode(index);
    }

    public void setSpace(final ISpace iSpace, final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        officeSingleLinkedList.setNode(iSpace, index);
    }

    public void addSpace(final ISpace iSpace, final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        addNode(iSpace, index);
    }

    public void removeSpace(final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        removeNode(index);
    }

    public ISpace getBestSpace() {
        ISpace office = null;
        double bestSpace = 0;
        int index = 0;
        for (int i = 0; i < officeSingleLinkedList.getSize(); i++) {
            if (officeSingleLinkedList.getNode(i).getSquare() > bestSpace) {
                bestSpace = officeSingleLinkedList.getNode(i).getSquare();
                office = officeSingleLinkedList.getNode(i);
            }
        }
        return office;
    }
}
