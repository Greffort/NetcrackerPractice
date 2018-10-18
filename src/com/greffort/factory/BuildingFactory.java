package com.greffort.factory;

import com.greffort.building.Office;
import com.greffort.building.OfficeBuilding;
import com.greffort.building.OfficeFloor;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public class BuildingFactory implements Factory {

    public Space createSpace(final double square, final int roomCount){
        return new Office(square,roomCount);
    }
    public Floor createFloor(@NotNull final Space[] spaces){
        return new OfficeFloor(spaces);
    }
    public Building createBuilding(final Floor[] officeFloors){
        return new OfficeBuilding(officeFloors);
    }



}
