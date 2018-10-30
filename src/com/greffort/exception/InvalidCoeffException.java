package com.greffort.exception;

public class InvalidCoeffException  extends IllegalArgumentException {
    public InvalidCoeffException() {
        super("Ошибка некорретного значения коэфициэнта");
    }
}
