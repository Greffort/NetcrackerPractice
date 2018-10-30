package com.greffort.interfaces;

import java.util.Iterator;

public interface Floor extends Iterable<Space> /*, Comparable<Space>*/{

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
