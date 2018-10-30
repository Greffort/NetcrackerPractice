package com.greffort.factory;

import com.greffort.buildings.dwelling.*;
import com.greffort.interfaces.*;
import org.jetbrains.annotations.NotNull;

public class DwellingFactory implements Factory {

    public Flat createSpace(final double square, final int roomCount) {
        return new Flat(square, roomCount);
    }

    public Space createSpace(Space space) {
        return new Flat(space.getSquare(), space.getRoomCount());
    }

    public DwellingFloor createFloor(@NotNull final Space[] offices) {
        return new DwellingFloor(offices);
    }

    public Dwelling createBuilding(final Floor[] officeFloors) {
        return new Dwelling(officeFloors);
    }

    public Dwelling createBuilding(final int numberOfficeFloors, final int[] numberOfficeFloor) {
        return new Dwelling(numberOfficeFloors, numberOfficeFloor);
    }
}
