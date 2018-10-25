package com.greffort.interfaces;

public interface Floor {
    /**
     * //    Создайте интерфейс Floor этажа здания, работающий со ссылками типа Space.
     * //    Интерфейс должен соответствовать общей функциональности DwellingFloor и OfficeFloor и должен содержать следующие  методы:
     * //            •	получения количества помещений на этаже,
     * //            •	получения общей площади помещений на этаже,
     * //            •	получения общего количества комнат в помещениях этажа,
     * //            •	получения массива всех помещений этажа,
     * //            •	получения помещения по его номеру,
     * //            •	изменения помещения по его номеру и ссылке на новое помещение,
     * //            •	вставки помещения по его номеру и ссылке на новое помещение,
     * //            •	удаления помещения по его номеру,
     * //            •	получения лучшего помещения на этаже.
     * //            Классы, соответственно, должны реализовывать интерфейс и работать со ссылками типа Space (с возможностью, например,
     * //            добавить на жилой этаж офисное помещение).
     * //    Рекомендуется использовать возможности рефакторинга среды разработки.
     */

    int getCountSpace();

    double getTotalSquare();

    int getTotalRoomCount();

    Space[] getSpaces();

    Space getSpace(final int index);

    void setSpace(final Space iSpace, final int index);

    void addSpace(final Space iSpace, final int index);

    void removeSpace(final int index);

    Space getBestSpace();

    Object clone() throws CloneNotSupportedException;
}
