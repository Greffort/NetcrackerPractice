package com.greffort.factory;

import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public interface Factory {

    Space createSpace(final double square, final int roomCount);
    Floor createFloor(@NotNull final Space[] offices);
    Building createBuilding(final Floor[] officeFloors);



}
