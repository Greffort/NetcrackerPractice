package com.greffort.buildings.office;

import com.greffort.exception.*;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public final class Office implements Space, Serializable {

    /*
    Создайте класс Office офиса офисного здания.
    Офис не хранит свой номер.
    Разные офисы могут иметь разные площади.
    Разные офисы могут иметь разное количество комнат.

    Конструктор по умолчанию создает офис из 1 комнаты площадью 250 кв.м. (константы)
    Конструктор может принимать площадь офиса (создается офис с 1 комнатой).
    Конструктор может принимать площадь офиса и количество комнат.
    Создайте метод получения количества комнат в офисе.
    Создайте метод изменения количества комнат в офисе.
    Создайте метод получения площади офиса.
    Создайте метод изменения площади офиса.
    */
    private static final double DEFAULT_SQUARE = 250;
    private static final int DEFAULT_ROOM_COUNT = 1;
    private double square;
    private int roomCount;

    public Office() {
        this(DEFAULT_SQUARE);
    }

    public Office(final double square) {
        this(square, DEFAULT_ROOM_COUNT);
    }

    public Office(final double square, final int roomCount) {
        setSquare(square);
        setRoomCount(roomCount);
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(final int roomCount) {
        if (roomCount < 0) {
            throw new InvalidRoomsCountException();
        }
        this.roomCount = roomCount;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(final double square) {
        if (square < 0) {
            throw new InvalidSpaceAreaException();
        }
        this.square = square;
    }

    @NotNull
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append("Office (" + getRoomCount() + ", " + getSquare() + ")").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Double.compare(office.square, square) == 0 && roomCount == office.roomCount;
    }

    @Override
    public int hashCode() {
        int p = 43;
        long squareBit = Double.doubleToRawLongBits(this.square);
        int squareBit2 = (int) (squareBit >>> 32);
        return roomCount * p | (int) squareBit | squareBit2;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Office(this.square, this.roomCount);
    }
}
