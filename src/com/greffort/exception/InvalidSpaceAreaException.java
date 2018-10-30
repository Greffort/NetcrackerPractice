package com.greffort.exception;

/**
 * Ошибка некорретной площади помещения
 */
public final class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
        super("Ошибка некорретной площади помещения");
    }
}
