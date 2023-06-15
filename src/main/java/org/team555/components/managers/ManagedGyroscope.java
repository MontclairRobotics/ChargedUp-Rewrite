package org.team555.components.managers;

import org.team555.util.frc.commandrobot.ManagerBase;

import com.kauailabs.navx.frc.AHRS;

public class ManagedGyroscope extends ManagerBase {
    private double offsetAngle;
    final AHRS navx = new AHRS();
    public ManagedGyroscope() {
        offsetAngle = navx.getAngle();
        navx.zeroYaw();
    }
    @Override
    public void always() {
        
    }

    public double getAngle() {
        return navx.getAngle() - offsetAngle;
    }

    //Zeros the gyroscope facing north
    public void zero() {
        zeroNorth();
    }

    public void zeroNorth() {

    }
    
}