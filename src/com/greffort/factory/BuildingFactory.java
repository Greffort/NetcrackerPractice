package com.greffort.factory;

import com.greffort.interfaces.*;
import org.jetbrains.annotations.NotNull;

public interface BuildingFactory {

    Space createSpace(final double square, final int roomCount);

    Space createSpace(Space space);

    Space createSpace(double area);

    Floor createFloor(@NotNull final Space[] spaces);

    Floor createFloor(int spacesCount);

    Building createBuilding(final Floor[] floors);

    Building createBuilding(final int floorsCount, final int[] spacesCounts);


}