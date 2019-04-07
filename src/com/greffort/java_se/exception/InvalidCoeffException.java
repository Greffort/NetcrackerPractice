package com.greffort.java_se.exception;

public class InvalidCoeffException  extends IllegalArgumentException {
    public InvalidCoeffException() {
        super("Ошибка некорретного значения коэфициэнта");
    }
}
