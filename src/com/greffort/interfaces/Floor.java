package com.greffort.interfaces;

public interface Floor extends Iterable<Space> , Comparable<Floor> {

    int getCountSpace();

    double getTotalSquare();

    int getTotalRoomCount();

    Space[] getSpaces();

    Space getSpace(final int index);

    void setSpace(final Space iSpace, final int index);

    void addSpace(final Space iSpace, final int index);

    void removeSpace(final int index);

    Space getBestSpace();

    Object clone() throws CloneNotSupportedException;
}
