package com.greffort.interfaces;

public interface ISpace {
//    Создайте интерфейс c помещения здания.
//    Интерфейс должен соответствовать общей функциональности Flat и Office и содержать следующие методы:
//            •	получения  количества комнат,
//            •	изменения количества комнат,
//            •	получения площади,
//            •	изменения площади.
//    Классы, соответственно, должны реализовывать интерфейс (при необходимости измените классы).
//    Рекомендуется использовать возможности рефакторинга среды разработки.

    int getRoomCount();

    void setRoomCount(int roomCount);

    double getSquare();

    void setSquare(double square);

}
