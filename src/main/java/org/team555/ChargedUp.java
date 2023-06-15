package org.team555;

import org.team555.util.frc.commandrobot.RobotContainer;

import com.kauailabs.navx.frc.AHRS;

public class ChargedUp extends RobotContainer {
    final AHRS navx = new AHRS();
    @Override
    public void initialize() {
        
    }

    public AHRS getNavx() {
        return navx;
    }

    
}
