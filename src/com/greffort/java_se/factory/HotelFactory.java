package com.greffort.java_se.factory;

import com.greffort.java_se.buildings.dwelling.Flat;
import com.greffort.java_se.buildings.dwelling.Hotel;
import com.greffort.java_se.buildings.dwelling.HotelFloor;
import com.greffort.java_se.interfaces.Building;
import com.greffort.java_se.interfaces.Floor;
import com.greffort.java_se.interfaces.Space;
import org.jetbrains.annotations.NotNull;

public class HotelFactory implements BuildingFactory {

    @Override
    public Space createSpace(double square, int roomCount) {
        return new Flat(square, roomCount);
    }

    @Override
    public Space createSpace(Space space) {
        return new Flat(space.getSquare(), space.getRoomCount());
    }

    @Override
    public Space createSpace(double area) {
        return new Flat(area);
    }

    @Override
    public Floor createFloor(@NotNull Space[] spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return null;
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Hotel(floors);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Hotel(floorsCount, spacesCounts);
    }
}
