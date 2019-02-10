package com.greffort.buildings;

import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author DecoratorFloor
 */
public class DecoratorFloor implements Floor {

    Floor decoratorFloor;

    public DecoratorFloor(Floor floor) {
        this.decoratorFloor = floor;
    }

    @Override
    public int getCountSpace() {
        return decoratorFloor.getCountSpace();
    }

    @Override
    public double getTotalSquare() {
        return decoratorFloor.getTotalSquare();
    }

    @Override
    public int getTotalRoomCount() {
        return decoratorFloor.getTotalRoomCount();
    }

    @Override
    public Space[] getSpaces() {
        return decoratorFloor.getSpaces();
    }

    @Override
    public Space getSpace(int index) {
        return decoratorFloor.getSpace(index);
    }

    @Override
    public void setSpace(Space iSpace, int index) {
        decoratorFloor.setSpace(iSpace, index);
    }

    @Override
    public void addSpace(Space iSpace, int index) {
        decoratorFloor.addSpace(iSpace, index);
    }

    @Override
    public void removeSpace(int index) {
        decoratorFloor.removeSpace(index);
    }

    @Override
    public Space getBestSpace() {
        return decoratorFloor.getBestSpace();
    }

    @Override
    public int compareTo(@NotNull Floor o) {
        return decoratorFloor.compareTo(o);
    }

    @NotNull
    @Override
    public Iterator<Space> iterator() {
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return decoratorFloor.clone();
    }
}
