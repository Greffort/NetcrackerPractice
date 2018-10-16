package com.greffort.interfaces;

public interface Building {
    /**
     * //    Создайте интерфейс Building здания, работающий со ссылками типа Floor и Space.
     * //    Интерфейс соответствует общей функциональности Dwelling и OfficeBuilding и должен содержать следующие методы:
     * //            •	получения количества этажей в здании,
     * //            •	получения количества помещений в здании,
     * //            •	получения общей площади всех помещений здания,
     * //            •	получения общего количества комнат в помещениях здания,
     * //            •	получения массива этажей здания,
     * //            •	получения этажа здания по его номеру,
     * //            • 	изменения этажа по его номеру и ссылке на новый этаж,
     * //            •	получения помещения по его номеру в здании,
     * //            •	изменения помещения в здании по номеру и ссылке на новое помещение,
     * //            •	вставке помещения в здании по будущему номеру и ссылке на новое помещение,
     * //          ф •	удаления помещения из здания,
     * //            •	получения лучшего помещения в здании,
     * //            •	получения отсортированного массива всех помещений.
     * //    Классы зданий, соответственно, должны реализовывать интерфейс и работать со ссылками типа Space и Floor (с возможностью, например,
     * //    заменить в офисном здании этаж на жилой).
     * //    Рекомендуется использовать возможности рефакторинга среды разработки.
     * //    Используйте методы, принимающие в качестве параметра массивы, как методы с аргументом переменной длины.
     */

    int getCountFloors();

    int getCountSpaces();

    double getTotalSquare();

    int getTotalRoomCount();

    Floor[] getFloors();

    Floor getFloor(final int index);

    void setFloor(final Floor iFloor, final int index);

    Space getSpace(final int index);

    void setSpace(final Space iSpace, final int index);

    void addSpace(final Space iSpace, final int index);

    void removeSpace(final int index);

    Space getBestSpace();

    Space[] getSortSpaces();
}
