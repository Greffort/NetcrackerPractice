package com.greffort.exception;

/**
 * Ошибка некорретного количества комнат в помещении
 */
public final class InvalidRoomsCountException extends IllegalArgumentException {
    //
    public InvalidRoomsCountException() {
        super("Ошибка некорретного количества комнат в помещении");
    }
}
