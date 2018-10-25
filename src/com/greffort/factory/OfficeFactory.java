package com.greffort.factory;

import com.greffort.buildings.office.Office;
import com.greffort.buildings.office.OfficeBuilding;
import com.greffort.buildings.office.OfficeFloor;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public class OfficeFactory implements Factory {
    public Space createSpace(final double square, final int roomCount){
        return new Office(square,roomCount);
    }
    public Space createSpace(Space space){
        return new Office(space.getSquare(), space.getRoomCount());
    }
    public Floor createFloor(@NotNull final Space[] spaces){
        return new OfficeFloor(spaces);
    }
    public Building createBuilding(final Floor[] officeFloors){
        return new OfficeBuilding(officeFloors);
    }
    public Building createBuilding(final int numberOfficeFloors, final int[] numberOfficeFloor){
        return new OfficeBuilding(numberOfficeFloors,numberOfficeFloor);
    }
}
