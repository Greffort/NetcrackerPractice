package com.greffort.java_se.factory;

import com.greffort.java_se.buildings.dwelling.Dwelling;
import com.greffort.java_se.buildings.dwelling.DwellingFloor;
import com.greffort.java_se.buildings.dwelling.Flat;
import com.sun.istack.internal.NotNull;
import com.greffort.java_se.interfaces.Floor;
import com.greffort.java_se.interfaces.Space;

public class DwellingFactory implements BuildingFactory {

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

    public Space createSpace(double area) {
        return new Flat(area);
    }

    public Floor createFloor(int spacesCount) {
        return new DwellingFloor(spacesCount);
    }

}
