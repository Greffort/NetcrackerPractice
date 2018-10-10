package com.greffort.exception;

public final class InvalidRoomsCountException extends IllegalArgumentException {
    //Ошибка некорретного количества комнат в помещении
    public InvalidRoomsCountException() {
        super("Ошибка некорретного количества комнат в помещении");
    }
}
