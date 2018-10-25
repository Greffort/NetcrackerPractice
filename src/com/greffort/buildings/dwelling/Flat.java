package com.greffort.buildings.dwelling;

import com.greffort.exception.InvalidRoomsCountException;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public final class Flat implements Space, Serializable {

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


    /**
     * Добавьте в классы помещений Flat и Office реализации метода String toString(),
     * выводящего тип помещения, текущее количество комнат помещения и его площадь через запятую в скобках.
     * Например, Flat (3, 55.0)
     */
    @NotNull
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(getClass() + " (" + getRoomCount() + ", " + getSquare() + ")").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(flat.square, square) == 0 &&
                roomCount == flat.roomCount;
    }

    /**
     * Добавьте в классы помещений реализации методов int hashCode().
     * Значение хеш-функции помещения можно вычислить
     * как  значение последовательного побитового исключающегоИЛИ битового представления количества комнат помещения,
     * и, например, первых 4 байтов и вторых 4-х байтов (для типа double)
     * битовых представлений площадей помещений этажа (следует воспользоваться вспомогательными методами классов-оберток).
     *
     * @return
     */
    @Contract(pure = true)
    @Override
    public int hashCode() {
        int p = 17;
        long squareBit = Double.doubleToRawLongBits(this.square);
        int squareBit2 = (int) (squareBit >>> 32);
        return roomCount * p | (int) squareBit | squareBit2;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
