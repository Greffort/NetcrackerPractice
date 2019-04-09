package com.greffort.java_se.comparators;


import com.greffort.java_se.interfaces.Floor;

import java.util.Comparator;

public class ComparatorFloor implements Comparator<Floor> {

    @Override
    public int compare(Floor o1, Floor o2) {
        if (o1.getTotalSquare() > o2.getTotalSquare()) {
            return -1;
        }
        if (o1.getTotalSquare() < o2.getTotalSquare()) {
            return 1;
        } else {
            return 0;
        }
    }
}
