package com.greffort.java_se.exception;

/**
 * Ошибка некорретной площади помещения
 */
public final class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
        super("Ошибка некорретной площади помещения");
    }
}
