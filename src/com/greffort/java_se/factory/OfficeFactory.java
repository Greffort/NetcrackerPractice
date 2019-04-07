package com.greffort.java_se.factory;

import com.greffort.java_se.buildings.office.Office;
import com.greffort.java_se.buildings.office.OfficeBuilding;
import com.greffort.java_se.buildings.office.OfficeFloor;
import com.greffort.java_se.interfaces.Building;
import com.greffort.java_se.interfaces.Floor;
import com.greffort.java_se.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public class OfficeFactory implements BuildingFactory {
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

    @Override
    public Space createSpace(double area) {
        return new Office(area);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new OfficeFloor(spacesCount);
    }
}
