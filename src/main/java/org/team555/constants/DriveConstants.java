package org.team555.constants;

import org.team555.math.Units555;
import org.team555.util.frc.SwerveModuleSpec;
import org.team555.util.frc.Tunable;

import com.pathplanner.lib.auto.PIDConstants;
import com.swervedrivespecialties.swervelib.MotorType;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public class DriveConstants {
    public static final double MAX_VOLTAGE_V = 12.0;

    public static final double MAX_SPEED_MPS = Units.feetToMeters(11);
    public static final double MAX_ACCEL_MPS2 = Units555.miphpsToMpsps(10);
    public static final double MAX_TURN_SPEED_RAD_PER_S = Math.PI * 2;
    public static final double MAX_TURN_ACCEL_RAD_PER_S2 = Units.degreesToRadians(360);

    public static final double WHEEL_BASE_W_M = Units.inchesToMeters(27);
    public static final double WHEEL_BASE_H_M = Units.inchesToMeters(30);

    private static Translation2d FLPosition = new Translation2d(+WHEEL_BASE_H_M / 2, +WHEEL_BASE_W_M / 2); // FL
    private static Translation2d FRPosition = new Translation2d(+WHEEL_BASE_H_M / 2, -WHEEL_BASE_W_M / 2); // FR
    private static Translation2d BLPosition = new Translation2d(-WHEEL_BASE_H_M / 2, +WHEEL_BASE_W_M / 2); // BL
    private static Translation2d BRPosition = new Translation2d(-WHEEL_BASE_H_M / 2, -WHEEL_BASE_W_M / 2); // BR

    public static SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
            new Translation2d[] { FLPosition, FRPosition, BLPosition, BRPosition });

    public static final int FRONT_LEFT_DRIVE_PORT = 10;
    public static final int FRONT_RIGHT_DRIVE_PORT = 1;
    public static final int BACK_LEFT_DRIVE_PORT = 3;
    public static final int BACK_RIGHT_DRIVE_PORT = 41;

    public static final int FRONT_LEFT_STEER_PORT = 7;
    public static final int FRONT_RIGHT_STEER_PORT = 18;
    public static final int BACK_LEFT_STEER_PORT = 28;
    public static final int BACK_RIGHT_STEER_PORT = 29;

    public static final int FRONT_LEFT_ENCODER_PORT = 12;
    public static final int FRONT_RIGHT_ENCODER_PORT = 13;
    public static final int BACK_LEFT_ENCODER_PORT = 11;
    public static final int BACK_RIGHT_ENCODER_PORT = 4;

    public static final double FRONT_LEFT_OFFSET = 268.242188;
    public static final double FRONT_RIGHT_OFFSET = 305.771484;
    public static final double BACK_LEFT_OFFSET = 250.048828;
    public static final double BACK_RIGHT_OFFSET = 149.765625;

    public static final double MAX_VOLTAGE = 12.0;
    public static final double MAX_VELOCITY_METERS_PER_SECOND = Units.feetToMeters(11);

    public static final Tunable<Boolean> DRIVE_INVERT = Tunable.of(false, "drive.invert");
    public static final Tunable<Boolean> STEER_INVERT = Tunable.of(false, "drive.invert_steer");

    public static final Tunable<Double> TIME_TO_STOP = Tunable.of(0.25, "drive.time_to_stop");

    public static double getRateLimit() {
        return 1.0 / TIME_TO_STOP.get();
    }

    // TODO add whenUpdate for changing drive inverts
    public static final SwerveModuleSpec frontLeftModule = new SwerveModuleSpec(
            SdsModuleConfigurations.MK4I_L1, MotorType.FALCON,
            DriveConstants.FRONT_LEFT_DRIVE_PORT, DriveConstants.DRIVE_INVERT.get(),
            MotorType.NEO, DriveConstants.FRONT_LEFT_STEER_PORT, DriveConstants.STEER_INVERT.get(),
            DriveConstants.FRONT_LEFT_ENCODER_PORT, DriveConstants.FRONT_LEFT_OFFSET);

    public static final SwerveModuleSpec frontRightModule = new SwerveModuleSpec(
            SdsModuleConfigurations.MK4I_L1, MotorType.FALCON,
            DriveConstants.FRONT_RIGHT_DRIVE_PORT, DriveConstants.DRIVE_INVERT.get(),
            MotorType.NEO, DriveConstants.FRONT_RIGHT_STEER_PORT, DriveConstants.STEER_INVERT.get(),
            DriveConstants.FRONT_RIGHT_ENCODER_PORT, DriveConstants.FRONT_RIGHT_OFFSET);

    public static final SwerveModuleSpec backLeftModule = new SwerveModuleSpec(
            SdsModuleConfigurations.MK4I_L1, MotorType.FALCON,
            DriveConstants.BACK_LEFT_DRIVE_PORT, DriveConstants.DRIVE_INVERT.get(),
            MotorType.NEO, DriveConstants.BACK_LEFT_STEER_PORT, DriveConstants.STEER_INVERT.get(),
            DriveConstants.BACK_LEFT_ENCODER_PORT, DriveConstants.BACK_LEFT_OFFSET);

    public static final SwerveModuleSpec backRightModule = new SwerveModuleSpec(
            SdsModuleConfigurations.MK4I_L1, MotorType.FALCON,
            DriveConstants.BACK_RIGHT_DRIVE_PORT, DriveConstants.DRIVE_INVERT.get(),
            MotorType.NEO, DriveConstants.BACK_RIGHT_STEER_PORT, DriveConstants.STEER_INVERT.get(),
            DriveConstants.BACK_RIGHT_ENCODER_PORT, DriveConstants.BACK_RIGHT_OFFSET);

    public static final SwerveModuleSpec[] MODULES = {
            frontLeftModule,
            frontRightModule,
            backLeftModule,
            backRightModule
    };

    public static final Double[][] SPEED_PRESETS = new Double[][] {
            { 1.0, 1.0 },
            { 0.75, 0.75 },
            { 0.5, 0.5 },
            { 0.25, 0.25 },
    };

    public static class PosPID {
        public static final Tunable<Double> KP = Tunable.of(3.1, "drive.pos.kp");
        public static final Tunable<Double> KI = Tunable.of(0.0, "drive.pos.ki");
        public static final Tunable<Double> KD = Tunable.of(0.25, "drive.pos.kd");

        public static PIDConstants consts() {
            return new PIDConstants(
                    KP.get(),
                    KI.get(),
                    KD.get());
        }
    }
    public static class ThetaPID
    {
        // public static final Tunable<Double> KP = Tunable.of(0.54, "drive.theta.kp");
        // public static final Tunable<Double> KI = Tunable.of(0.02, "drive.theta.ki");
        // public static final Tunable<Double> KD = Tunable.of(0.08, "drive.theta.kd");
        public static final Tunable<Double> KP = Tunable.of(0.1, "drive.theta.kp");
        public static final Tunable<Double> KI = Tunable.of(0.0, "drive.theta.ki");
        public static final Tunable<Double> KD = Tunable.of(0.0, "drive.theta.kd");
        
        public static PIDConstants consts()
        {
            return new PIDConstants(
                    KP.get(),
                    KI.get(),
                    KD.get());
        }

        public static final Tunable<Double> KAutoP = Tunable.of(5, "drive.auto.theta.kp"); // 3.5
        public static final Tunable<Double> KAutoI = Tunable.of(0.02, "drive.auto.theta.ki");
        public static final Tunable<Double> KAutoD = Tunable.of(0.4, "drive.auto.theta.kd"); // 0.22

        public static PIDConstants autoconsts() {
            return new PIDConstants(
                    KAutoP.get(),
                    KAutoI.get(),
                    KAutoD.get());
        }
    }

}
