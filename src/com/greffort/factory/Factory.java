package com.greffort.factory;

import com.greffort.interfaces.*;
import org.jetbrains.annotations.NotNull;

public interface Factory {

    Space createSpace(final double square, final int roomCount);

    Space createSpace(Space space);

    Floor createFloor(@NotNull final Space[] offices);

    Building createBuilding(final Floor[] officeFloors);

    Building createBuilding(final int numberOfficeFloors, final int[] numberOfficeFloor);
}
