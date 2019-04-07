package com.greffort.java_se.interfaces;

public interface Building extends Iterable<Floor> {

    int getCountFloors();

    int getCountSpaces();

    double getTotalSquare();

    int getTotalRoomCount();

    Floor[] getFloors();

    Floor getFloor(final int index);

    void setFloor(final Floor iFloor, final int index);

    Space getSpace(final int index);

    void setSpace(final Space iSpace, final int index);

    void addSpace(final Space iSpace, final int index);

    void removeSpace(final int index);

    Space getBestSpace();

    Space[] getSortSpaces();

    Object clone() throws CloneNotSupportedException;

}
