package com.greffort.comparators;

        import com.greffort.interfaces.Space;

        import java.util.Comparator;

public class ComparatorSpace implements Comparator<Space> {
    public int compare(Space o1, Space o2) {
        return (-1) * o1.compareTo(o2);
    }
}
