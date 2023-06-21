package org.team555.components.subsystems;

import org.team555.ChargedUp;
import org.team555.constants.ControllerConstants;
import org.team555.constants.DriveConstants;
import org.team555.inputs.JoystickInput;
import org.team555.math.Math555;
import org.team555.util.Unimplemented;
import org.team555.util.frc.Logging;
import org.team555.util.frc.PIDMechanism;
import org.team555.util.frc.SwerveModuleSpec;
import org.team555.util.frc.commandrobot.ManagerSubsystemBase;

import com.swervedrivespecialties.swervelib.MkSwerveModuleBuilder;
import com.swervedrivespecialties.swervelib.MotorType;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;

import static org.team555.constants.DriveConstants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Drivetrain extends ManagerSubsystemBase {

    private final SwerveDrivePoseEstimator poseEstimator;

    private boolean fieldRelative = true;
    private ChassisSpeeds chassisSpeeds;

    private double xSpeed;
    private double ySpeed;
    private double thetaSpeed;

    private SlewRateLimiter xRateLimiter;
    private SlewRateLimiter yRateLimiter;
    private SlewRateLimiter thetaRateLimiter;

    private PIDMechanism xPID;
    private PIDMechanism yPID;
    private PIDMechanism thetaPID;

    private final SwerveModule[] modules = new SwerveModule[4];;

    public Drivetrain() {
        //TODO why do they have each module on shuffleboard?
        for (int i = 0; i < DriveConstants.MODULES.length; i++) {
            modules[i] = DriveConstants.MODULES[i].build();
        }

        poseEstimator = new SwerveDrivePoseEstimator(DriveConstants.KINEMATICS, ChargedUp.gyroscope.getRobotRotationCounterClockwise(), getModulePositions(), new Pose2d());

        xRateLimiter = new SlewRateLimiter(DriveConstants.getRateLimit());
        yRateLimiter = new SlewRateLimiter(DriveConstants.getRateLimit());
        thetaRateLimiter = new SlewRateLimiter(DriveConstants.getRateLimit());
        
        PIDController xController = new PIDController(PosPID.KP.get(), PosPID.KI.get(), PosPID.KD.get());
        PIDController yController = new PIDController(PosPID.KP.get(), PosPID.KI.get(), PosPID.KD.get());
        PIDController thetaController = new PIDController(ThetaPID.KP.get(), ThetaPID.KI.get(), ThetaPID.KD.get());

        thetaController.setTolerance(Math.toRadians(1.5), Math.toRadians(0.5));
        thetaController.enableContinuousInput(0, 2*Math.PI);

        PosPID.KP.whenUpdate(xController::setP).whenUpdate(yController::setP);
        PosPID.KI.whenUpdate(xController::setI).whenUpdate(yController::setI);
        PosPID.KD.whenUpdate(xController::setD).whenUpdate(yController::setD);

        ThetaPID.KP.whenUpdate(thetaController::setP);
        ThetaPID.KI.whenUpdate(thetaController::setI);
        ThetaPID.KD.whenUpdate(thetaController::setD);

        xPID = new PIDMechanism(xController);
        yPID = new PIDMechanism(yController);
        thetaPID = new PIDMechanism(thetaController);

        
    }

    public void driveFromInputs(JoystickInput turn, JoystickInput drive) {
        ControllerConstants.TURN_ADJUSTER.adjustX(turn);
        ControllerConstants.TURN_ADJUSTER.adjustMagnitude(drive);

        driveFromSpeeds(drive.getX(), drive.getY(), turn.getX());
    }

    public void driveFromSpeeds(double xSpeed, double ySpeed, double thetaSpeed) {
        xSpeed = xSpeed / MAX_VELOCITY_METERS_PER_SECOND;
        ySpeed = ySpeed / MAX_VELOCITY_METERS_PER_SECOND;
        thetaSpeed = thetaSpeed / MAX_TURN_SPEED_RAD_PER_S;

        xSpeed = Math555.clamp1(xSpeed);
        ySpeed = Math555.clamp1(ySpeed);
        thetaSpeed = Math555.clamp1(thetaSpeed);

        xPID.setSpeed(xSpeed);
        yPID.setSpeed(ySpeed);
        thetaPID.setSpeed(thetaSpeed);
    }

    public void enableFieldRelative() {
        fieldRelative = true;
        Logging.info("Field relative enabled");
    }

    public void disableFieldRelative() {
        fieldRelative = false;
        Logging.info("Field relative disabled");
    }

    public SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] arr = new SwerveModulePosition[4];
        for (int i = 0; i < modules.length; i++) {
            arr[i] = modules[i].getPosition();
        }
        return arr;
    }

    @Override
    public void always() { // TODO why does other repository use periodic? Same thing?

        poseEstimator.update(ChargedUp.gyroscope.getRobotRotationCounterClockwise(), getModulePositions());

        xPID.setMeasurement(poseEstimator.getEstimatedPosition().getX());
        yPID.setMeasurement(poseEstimator.getEstimatedPosition().getY());
        thetaPID.setMeasurement(poseEstimator.getEstimatedPosition().getRotation().getRadians() % (2 * Math.PI));


        xPID.update();
        yPID.update();
        thetaPID.update();

        //TODO account for speeds over max
        double xLimited = xRateLimiter.calculate(xPID.getSpeed());
        double yLimited = yRateLimiter.calculate(yPID.getSpeed());
        double thetaLimited = thetaRateLimiter.calculate(thetaPID.getSpeed());

        
        if (fieldRelative) {
            this.chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(new ChassisSpeeds(xLimited, yLimited, thetaLimited), ChargedUp.gyroscope.getRobotRotationCounterClockwise());
        } else {
            this.chassisSpeeds = new ChassisSpeeds(xLimited, yLimited, thetaLimited);
        }

        SwerveModuleState[] moduleStates = DriveConstants.KINEMATICS.toSwerveModuleStates(chassisSpeeds);

        for (int i = 0; i < modules.length; i++) {
            modules[i].set(moduleStates[i].speedMetersPerSecond * DriveConstants.MAX_VOLTAGE, moduleStates[i].angle.getRadians());
        }
    }

}
