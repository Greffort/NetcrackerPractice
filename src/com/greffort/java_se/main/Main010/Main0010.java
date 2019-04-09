package com.greffort.java_se.main.Main010;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

//        com.greffort.java_ee.java_se.com.greffort.main.Main010 kek 1 2 3 4 5
public class Main0010 {
    public static void main(@NotNull final String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Class<?> aClass = Class.forName(args[0]);
        final double[] doubles = Arrays.stream(args).skip(2).mapToDouble(Double::valueOf).toArray();
        final Method declaredMethod = aClass.getDeclaredMethod(args[1], double[].class);
        declaredMethod.invoke(null, doubles);
    }

    public static void kek(@NotNull final double... values) {
        for (double value : values) {
            System.out.println(value);
        }
    }
}
