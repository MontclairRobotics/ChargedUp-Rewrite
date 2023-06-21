package org.team555;

import org.team555.structure.Directions;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public class Commands555 {
    public static CommandBase zeroNAVX() {
        return Commands.runOnce(()-> ChargedUp.gyroscope.zeroNorth());
    }
    
    public static CommandBase turnToAbsoluteAngle(Rotation2d angle){
        return ChargedUp.drivetrain.thetaPID.goToSetpoint(angle.getRadians()).withTimeout(1.8);
    }


}
