package com.greffort.buildings.dwelling;

import com.greffort.exception.InvalidCoeffException;
import com.greffort.interfaces.Space;

public class HotelFloor extends DwellingFloor {

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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HotelFloor (" + getCountSpace() + ", " + (getStars()) + ", ");
        for (int i = 0; i < getCountSpace(); i++) {
            stringBuffer.append(getSpace(i).toString() + "), ");
        }
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelFloor)) return false;
        if (!super.equals(o)) return false;
        HotelFloor that = (HotelFloor) o;
        return getStars() == that.getStars();
    }

    @Override
    public int hashCode() {
        return getCountSpace() | getStars() | super.hashCode();
    }
}
