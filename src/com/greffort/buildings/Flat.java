package com.greffort.buildings;

import com.greffort.exception.InvalidRoomsCountException;
import com.greffort.exception.InvalidSpaceAreaException;
import com.greffort.interfaces.ISpace;

public final class Flat implements ISpace {

//    Задание 1
//    + Создайте публичный класс Flat квартиры жилого дома.
//    Квартира не хранит свой номер.
//    + Разные квартиры могут иметь разную площадь.
//    + Разные квартиры могут иметь разное количество комнат.
//    + Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м. (эти числа должны быть константами в классе)
//    + Конструктор может принимать площадь квартиры (создается квартира с 2 комнатами).
//    + Конструктор может принимать площадь квартиры и количество комнат.
//    Создайте метод получения количества комнат в квартире.
//    Создайте метод изменения количества комнат в квартире.
//    Создайте метод получения площади квартиры.
//    Создайте метод изменения площади квартиры.

    private static final double DEFAULT_SQUARE = 50;
    private static final int DEFAULT_ROOM_COUNT = 2;
    private double square;
    private int roomCount;

    public Flat() {
        this(DEFAULT_SQUARE);
    }

    public Flat(final double square) {
        this(square, DEFAULT_ROOM_COUNT);
    }

    public Flat(final double square, final int roomCount) {
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
            throw new InvalidRoomsCountException();
        }
        this.square = square;
    }
}
