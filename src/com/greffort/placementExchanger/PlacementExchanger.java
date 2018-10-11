package com.greffort.placementExchanger;

import com.greffort.exception.FloorIndexOutOfBoundsException;
import com.greffort.exception.IndexChangeableFloorsException;
import com.greffort.exception.IndexChangeableSpacesException;
import com.greffort.exception.SpaceIndexOutOfBoundsException;
import com.greffort.interfaces.Building;
import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;

public final class PlacementExchanger {

//    Создайте отдельный класс PlacementExchanger, работающий со ссылками типа Space, Floor, Building и содержащий следующие статические методы.

//      •	Метод проверки возможности обмена помещениями. Передаются две ссылки на объекты типа Space. Метод возвращает true,
//          если общая площадь и количество комнат в помещениях равны, и false в других случаях.

//      •	Метод проверки возможности обмена этажами. Методу передаются две ссылки на объекты типа Floor. Метод возвращает true,
//          если общая площадь этажей и количество помещений равны, и false в других случаях.

//      •	Метод обмена помещениями двух этажей public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
//          Метод должен проверять возможность обмена помещениями и допустимость номеров помещений, выбрасывать при необходимости
//          соответствующие исключения.

//      •	Метод обмена этажами двух зданий public static void
//          exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
//          Методу передаются две ссылки типа Building и номера соответствующих этажей. Метод должен проверять возможность обмена этажами
//          и допустимость номеров этажей, выбрасывать при необходимости соответствующие исключения.


    private static boolean checkRoomExchange(Space space1, Space space2) {
        if (space1.getSquare() == space2.getSquare() && space1.getRoomCount() == space2.getRoomCount()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkRoomExchange(Floor floor1, Floor floor2) {
        if (floor1.getTotalSquare() == floor2.getTotalSquare() && floor1.getTotalRoomCount() == floor2.getTotalRoomCount()) {
            return true;
        } else {
            return false;
        }
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws IndexChangeableSpacesException, SpaceIndexOutOfBoundsException {
        if (!checkRoomExchange(floor1.getSpace(index1), floor2.getSpace(index2))) {
            throw new IndexChangeableSpacesException();
        }
        if (index1 > floor1.getCountSpace() || index2 > floor2.getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException();
        } else {
            Space tempFloor = floor1.getSpace(index1);
            floor1.setSpace(floor2.getSpace(index2), index1);
            floor2.setSpace(tempFloor, index2);
        }
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws IndexChangeableFloorsException, FloorIndexOutOfBoundsException {
        if (!checkRoomExchange(building1.getFloor(index1), building2.getFloor(index2))) {
            throw new IndexChangeableFloorsException();
        }
        if (index1 > building1.getCountFloors() || index2 > building2.getCountFloors()) {
            throw new FloorIndexOutOfBoundsException();
        } else {
            Floor tempFloor = building1.getFloor(index1);
            building1.setFloor(building2.getFloor(index2), index1);
            building2.setFloor(tempFloor, index2);
        }

    }
}
