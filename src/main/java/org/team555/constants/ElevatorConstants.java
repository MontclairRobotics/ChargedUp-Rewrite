package org.team555.constants;

import edu.wpi.first.math.util.Units;

public class ElevatorConstants {
    public static final double GEAR_RATIO_OUT_OVER_IN = 1.0 / 40.0;
    public static final double SPROCKET_DIAMETER = Units.inchesToMeters(1.685);
    public static final double STAGE_COUNT = 3;

    //TODO how tf is this calculated?
    public static final double ENCODER_CONVERSION_FACTOR 
        = GEAR_RATIO_OUT_OVER_IN * SPROCKET_DIAMETER * Math.PI * STAGE_COUNT;

    public static final boolean INVERTED = true;

    public static final int ELEVATOR_PORT = 5;

    public static final double 
        KP = 0.75,
        KI = 0.1,
        KD = 0
    ;
}
