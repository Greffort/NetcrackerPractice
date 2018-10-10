package com.greffort.exception;

public final class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    //Ошибка выхода за границы номеров помещений
    public SpaceIndexOutOfBoundsException() {
        super("Ошибка выхода за границы номеров помещений");
    }
}
