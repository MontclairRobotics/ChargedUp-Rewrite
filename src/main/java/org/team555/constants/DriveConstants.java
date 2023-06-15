package org.team555.constants;

import org.team555.math.Units555;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public class DriveConstants {
    public static final double MAX_VOLTAGE_V = 12.0;

    public static final double MAX_SPEED_MPS             = Units.feetToMeters(11);
    public static final double MAX_ACCEL_MPS2            = Units555.miphpsToMpsps(10);
    public static final double MAX_TURN_SPEED_RAD_PER_S  = Math.PI * 2;
    public static final double MAX_TURN_ACCEL_RAD_PER_S2 = Units.degreesToRadians(360);

    public static final double WHEEL_BASE_W_M = Units.inchesToMeters(27);
    public static final double WHEEL_BASE_H_M = Units.inchesToMeters(30);

    private static Translation2d FLPosition = new Translation2d(+WHEEL_BASE_H_M/2, +WHEEL_BASE_W_M/2); //FL
    private static Translation2d FRPosition = new Translation2d(+WHEEL_BASE_H_M/2, -WHEEL_BASE_W_M/2); //FR
    private static Translation2d BLPosition = new Translation2d(-WHEEL_BASE_H_M/2, +WHEEL_BASE_W_M/2); //BL
    private static Translation2d BRPosition = new Translation2d(-WHEEL_BASE_H_M/2, -WHEEL_BASE_W_M/2); //BR

    public static SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(new Translation2d[]{FLPosition, FRPosition, BLPosition, BRPosition});
}
