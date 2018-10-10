package com.greffort.exception;

public final class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    //Ошибка выхода за границы номеров этажей
    public FloorIndexOutOfBoundsException() {
        super("Ошибка выхода за границы номеров этажей");
    }
}
