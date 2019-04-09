package com.greffort.java_se.buildings.dwelling;

import com.greffort.java_se.exception.InvalidRoomsCountException;
import com.greffort.java_se.interfaces.Space;
import com.sun.istack.internal.NotNull;

import java.io.Serializable;

public final class Flat implements Space, Serializable {

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

    @NotNull
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append("Flat" + " (" + getRoomCount() + ", " + getSquare() + ")").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(flat.square, square) == 0 &&
                roomCount == flat.roomCount;
    }

    @Override
    public int hashCode() {
        int p = 17;
        long squareBit = Double.doubleToRawLongBits(this.square);
        int squareBit2 = (int) (squareBit >>> 32);
        return roomCount * p | (int) squareBit | squareBit2;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Flat(this.square, this.roomCount);
    }

    public int compareTo(Space space) {
        /**
         * В классах помещений реализуйте метод int compareTo(T o) таким образом,
         * чтобы он сравнивал объекты помещений по их площади и считал бОльшим помещение с большей площадью.
         *
         * Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед тем, который передается
         * через параметр.
         * Если метод вернет положительное число, то, наоборот, после второго объекта.
         * Если метод возвратит ноль, значит, оба объекта равны.
         *
         * положительное, если вызывающий объект больше объекта, переданного в качестве параметра;
         * отрицательное, если вызывающий объект меньше объекта, переданного в качестве параметра;
         * нуль, если объекты равны.
         */
        if (this.getSquare() > space.getSquare()) {
            return 1;
        }
        if (this.getSquare() < space.getSquare()) {
            return -1;
        } else {
            return 0;
        }
    }
}
