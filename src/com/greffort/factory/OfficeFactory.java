package com.greffort.factory;

import com.greffort.buildings.office.*;
import com.greffort.interfaces.*;
import org.jetbrains.annotations.NotNull;

public class OfficeFactory implements Factory {
    public Space createSpace(final double square, final int roomCount) {
        return new Office(square, roomCount);
    }

    public Space createSpace(Space space) {
        return new Office(space.getSquare(), space.getRoomCount());
    }

    public Floor createFloor(@NotNull final Space[] spaces) {
        return new OfficeFloor(spaces);
    }

    public Building createBuilding(final Floor[] officeFloors) {
        return new OfficeBuilding(officeFloors);
    }

    public Building createBuilding(final int numberOfficeFloors, final int[] numberOfficeFloor) {
        return new OfficeBuilding(numberOfficeFloors, numberOfficeFloor);
    }
}
