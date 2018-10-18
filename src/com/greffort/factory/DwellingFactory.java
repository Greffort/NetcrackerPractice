package com.greffort.factory;

import com.greffort.dwelling.Dwelling;
import com.greffort.dwelling.DwellingFloor;
import com.greffort.dwelling.Flat;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public class DwellingFactory implements Factory {

    public Flat createSpace(final double square, final int roomCount){
        return new Flat(square,roomCount);
    }
    public DwellingFloor createFloor(@NotNull final Space[] offices){
        return new DwellingFloor(offices);
    }
    public Dwelling createBuilding(final Floor[] officeFloors){
        return new Dwelling(officeFloors);
    }

}
