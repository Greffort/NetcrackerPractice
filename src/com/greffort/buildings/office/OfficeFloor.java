package com.greffort.buildings.office;

import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import com.greffort.linkedList.SingleLinkedList;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
//import com.greffort.linkedList.DoubleLinkedList;

public final class OfficeFloor implements Floor, Serializable {

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

    private SingleLinkedList<Space> officeSingleLinkedList;


    public OfficeFloor(final int numberOffices) {
        officeSingleLinkedList = new SingleLinkedList<Space>();
        for (int i = 0; i < numberOffices; i++) {
            addNode(new Office(), i);
        }
    }

    public OfficeFloor(final SingleLinkedList<Space> officeSingleLinkedList) {
        this.officeSingleLinkedList = officeSingleLinkedList;
    }

    public OfficeFloor(@NotNull final Space[] offices) {
        this.officeSingleLinkedList = new SingleLinkedList<Space>();
        for (int i = 0; i < offices.length; i++) {
            addNode(offices[i], i);
        }
    }

    private void addNode(final Space iSpace, final int index) {
        officeSingleLinkedList.addNode(iSpace, index);//Exception in thread "main" java.lang.NullPointerException
    }

    private Space getNode(final int index) {
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

    public Space[] getSpaces() {
        Space[] offices = new Space[officeSingleLinkedList.getSize()];
        for (int i = 0; i < officeSingleLinkedList.getSize(); i++) {
            offices[i] = officeSingleLinkedList.getNode(i);
        }
        return offices;
    }

    public Space getSpace(final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return getNode(index);
    }

    public void setSpace(final Space space, final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        officeSingleLinkedList.setNode(space, index);
    }

    public void addSpace(final Space space, final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        addNode(space, index);
    }

    public void removeSpace(final int index) {
        if (index < 0 || index > officeSingleLinkedList.getSize()) {
            throw new SpaceIndexOutOfBoundsException();
        }
        removeNode(index);
    }

    public Space getBestSpace() {
        Space office = null;
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


    public String toString() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OfficeFloor (" + getCountSpace() + ", ");
        for (int i = 0; i < getCountSpace(); i++) {
            stringBuffer.append(getSpace(i).toString() + "), ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeFloor that = (OfficeFloor) o;
        boolean b = false;
        for (int i = 0; i < getCountSpace(); i++) {
            if (this.getSpace(i).equals(that.getSpace(i))) {
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
        int p = 43;
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
        return new OfficeFloor(spaces);
    }
}
