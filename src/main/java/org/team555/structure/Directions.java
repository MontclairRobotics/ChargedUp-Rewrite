package org.team555.structure;

public enum Directions {
    NORTH(0),
    SOUTH(180),
    EAST(-90),
    WEST(90);

    private double angle;


    private Directions(int angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }
}
