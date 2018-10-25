package com.greffort.buildings.dwelling;

import com.greffort.exception.InvalidCoeffException;
import com.greffort.interfaces.Space;

import java.util.Objects;

public class HotelFloor extends DwellingFloor{
    /**
     * Создайте пакет buildings.dwelling.hotel.
     *
     * Создайте класс HotelFloor, расширяющий класс DwellingFloor.
     *
     * Каждый этаж отеля имеет показатель «количество звезд».
     *
     * Разные этажи отеля могут иметь разные значения показателя количества звезд.
     *
     * Этаж отеля можно создать как по количеству помещений этажа, так и по массиву помещений.
     *
     * Количество звезд этажа при создании объекта должно браться из константы в классе, равной 1.
     *
     * У объекта этажа отеля должны быть методы получения и изменения его количества звезд.
     */
    private static final int DEFAULT_STARS = 1;
    private int stars;

    public HotelFloor(int countFlat) {
        super(countFlat);
        setStars(DEFAULT_STARS);
    }

    public HotelFloor(Space[] arrayFlat) {
        super(arrayFlat);
        setStars(DEFAULT_STARS);
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            throw new InvalidCoeffException();
        }
        this.stars = stars;
    }

    /**
     * Переопределите в классе HotelFloor метод String toString().
     * Метод выводит тип этажа,
     * значение показателя звездности этажа, текущее количество помещений этажа и соответствующую информацию по каждому помещению.
     * Например,
     * HotelFloor (5, 4, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0), Flat (2, 48.0))
     */

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HotelFloor (" + getCountSpace() + ", "+(getStars()) +", ");
        for (int i = 0; i < getCountSpace(); i++) {
            stringBuffer.append(getSpace(i).toString() + "), ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        return stringBuffer.toString();
    }

    @Override
    /**
     * Добавьте в класс HotelFloor реализацию метода boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является этажом отеля, количество помещений совпадает, сами помещения эквивалентны помещениям текущего объекта, а количество звезд совпадает.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelFloor)) return false;
        if (!super.equals(o)) return false;
        HotelFloor that = (HotelFloor) o;
        return getStars() == that.getStars();
    }

    /**
     * Добавьте в класс HotelFloor реализацию метода int hashCode(). Значение хеш-функции этажа отеля вычисляется как значение побитового
     * исключающего ИЛИ количества помещений на этаже, показателя звездности этажа и значений хеш-функций помещений этажа.
     */
    @Override
    public int hashCode() {
        return getCountSpace() | getStars() | super.hashCode();
    }
}
