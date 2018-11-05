package com.greffort.comparators;

import com.greffort.interfaces.Space;

import java.util.Comparator;

public  class ComparatorSpace implements Comparator<Space> {

    public  int compare(Space o1, Space o2) {
        if(o1.getRoomCount() > o2.getRoomCount()){
            return -1;
        }
        if(o1.getRoomCount() < o2.getRoomCount()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
