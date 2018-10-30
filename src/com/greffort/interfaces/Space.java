package com.greffort.interfaces;

public interface Space extends Comparable<Space> {

    int getRoomCount();

    void setRoomCount(final int roomCount);

    double getSquare();

    void setSquare(final double square);

    Object clone() throws CloneNotSupportedException;


}
