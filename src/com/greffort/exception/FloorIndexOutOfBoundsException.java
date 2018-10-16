package com.greffort.exception;

/**
 * Ошибка выхода за границы номеров этажей
 */
public final class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    //
    public FloorIndexOutOfBoundsException() {
        super("Ошибка выхода за границы номеров этажей");
    }
}
