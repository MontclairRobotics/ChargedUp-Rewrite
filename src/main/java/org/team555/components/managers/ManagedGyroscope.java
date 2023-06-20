package org.team555.components.managers;

import org.team555.constants.GyroConstants;
import org.team555.util.frc.commandrobot.ManagerBase;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;

public class ManagedGyroscope extends ManagerBase {

    final AHRS navx = new AHRS();
    public ManagedGyroscope() {
        navx.zeroYaw();
        //TODO maybe make always method to account for sensor calibration
        navx.setAngleAdjustment(GyroConstants.NORTH_ANGLE);
    }
    @Override
    public void always() {
        
    }

    public double getAngleClockwise() {
        return navx.getAngle();
    }

    public double getAngleCounterClockwise() {
        return 360 - navx.getAngle();
    }

    //Zeros the gyroscope facing north
    public void zero() {
        zeroNorth();
    }

    public void zeroNorth() {
        navx.zeroYaw();
        navx.setAngleAdjustment(GyroConstants.NORTH_ANGLE);
    }

    public void zeroSouth() {
        navx.zeroYaw();
        navx.setAngleAdjustment(GyroConstants.SOUTH_ANGLE);
    }

    public void zeroWest() {
        navx.zeroYaw();
        navx.setAngleAdjustment(GyroConstants.WEST_ANGLE);
    }

    public void zeroEast() {
        navx.zeroYaw();
        navx.setAngleAdjustment(GyroConstants.EAST_ANGLE);
    }


    /**
    * Returns a Rotation2d of the current cumulative clockwise angle of the robot
    **/
    public Rotation2d getRobotRotationClockwise() {
        return Rotation2d.fromDegrees(getAngleClockwise());
    }

    public Rotation2d getRobotRotationCounterClockwise() {
        return Rotation2d.fromDegrees(getAngleCounterClockwise());
    }
    
}