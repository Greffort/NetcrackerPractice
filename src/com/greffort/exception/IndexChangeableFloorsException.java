package com.greffort.exception;

/**
 * 	несоответствия обменивающихся этажей (объявляемое)
 */

public class IndexChangeableFloorsException extends Exception {
    public IndexChangeableFloorsException() {
        super("Ошибка несоответствия обменивающихся этажей");
    }
}
