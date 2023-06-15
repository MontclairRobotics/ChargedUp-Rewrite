package org.team555.components.subsystems;

import org.team555.ChargedUp;
import org.team555.constants.DriveConstants;
import org.team555.util.Unimplemented;
import org.team555.util.frc.commandrobot.ManagerSubsystemBase;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Rotation2d;

public class Drivetrain extends ManagerSubsystemBase {

    private final SwerveDrivePoseEstimator poseEstimator;

    public Drivetrain() {
        poseEstimator = new SwerveDrivePoseEstimator(DriveConstants.KINEMATICS, );
    }

    public void driveFromChassisSpeeds(int xSpeed, int ySpeed, int thetaSpeed) {

    }
    
    @Override 
    public void always() { //TODO why does other repository use periodic? Same thing?

    }

    public Rotation2d getRobotRotation() {
        // ChargedUp.getNavx().getAngle();
        return Unimplemented.here();
    }

}
