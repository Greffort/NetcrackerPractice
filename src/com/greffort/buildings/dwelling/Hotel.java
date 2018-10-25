package com.greffort.buildings.dwelling;

import com.greffort.interfaces.Floor;
import com.greffort.interfaces.Space;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Hotel extends Dwelling {
    /**
     *
     * Добавьте в класс HotelFloor реализацию метода int hashCode(). Значение хеш-функции этажа отеля вычисляется как значение побитового
     * исключающего ИЛИ количества помещений на этаже, показателя звездности этажа и значений хеш-функций помещений этажа.
     *
     * Добавьте в класс Hotel реализацию метода int hashCode(). Значение хеш-функции здания вычисляется как значение побитового
     * исключающего ИЛИ количества этажей здания и значений хеш-функций этажей отеля.
     */


    public Hotel(int dwellingFloorCount, @NotNull int[] arrayFlatCounts) {
        super(dwellingFloorCount, arrayFlatCounts);
    }

    public Hotel(Floor[] arrayDwellingFloor) {
        super(arrayDwellingFloor);
    }

    /**
     * Переопределите метод getBestSpace() у класса отеля.
     * Лучшим считается номер с максимальным значением показателя area*coeff, где area-площадь помещения,
     * coeff-определяется следующим образом.
     * * *	    0,25
     * * **	0,5
     * * ***	1
     * * ****	1,25
     * * *****	1,5
     *
     * @return
     */


    public int getStars() {
        int result = 0;
        for (int i = 0; i < getCountFloors(); i++) {
            if (getFloor(i) instanceof HotelFloor) {
                if (result > ((HotelFloor) getFloor(i)).getStars()) {
                    result = ((HotelFloor) getFloor(i)).getStars();
                }
            }
        }
        return result;
    }

    public Space getBestSpace() {
        Space result = null;
        Space space;
        double numberResult = 0;
        double coeff;
        for (int i = 0; i < getCountFloors(); i++) {
            Floor floor = getFloor(i);
            if (floor instanceof HotelFloor) {
                for (int j = 0; j < floor.getCountSpace(); j++) {
                    space = floor.getSpace(j);
                    coeff = ((HotelFloor) floor).getStars() / 4;
                    if (space.getSquare() * coeff > numberResult) {
                        result = space;
                    }
                }
            }
        }
        return result;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Hotel (" + getCountFloors() + ", " + getStars() + ", ");
        for (int i = 0; i < getCountFloors(); i++) {
            stringBuffer.append(getFloor(i).toString() + ", ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        if (!(((Hotel) o).getCountFloors()==getCountFloors())) return false;
        Hotel that = (Hotel) o;
        if(!(getStars() == that.getStars()))return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return getCountFloors() | super.hashCode();
    }
}
