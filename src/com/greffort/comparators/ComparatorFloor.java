package com.greffort.comparators;

import com.greffort.interfaces.Floor;

import java.util.Comparator;

/**
 * Опишите класс критерия, реализующий интерфейс java.util.Comparator таким образом,
 * чтобы он сравнивал этажи по общей площади помещений на этаже и считал бОльшим этаж с меньшей общей площадью помещений на этаже.
 */
public class ComparatorFloor implements Comparator<Floor> {

    @Override
    public int compare(Floor o1, Floor o2) {
        return (-1)*o1.compareTo(o2);
    }
}
