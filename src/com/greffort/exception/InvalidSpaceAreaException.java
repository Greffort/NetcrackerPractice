package com.greffort.exception;

public final class InvalidSpaceAreaException extends IllegalArgumentException {
    //Ошибка некорретной площади помещения
    public InvalidSpaceAreaException() {
        super("Ошибка некорретной площади помещения");
    }
}
