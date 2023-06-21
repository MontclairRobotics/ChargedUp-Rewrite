package org.team555;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public class Commands555 {
    public static CommandBase zeroNAVX() {
        return Commands.runOnce(()-> ChargedUp.gyroscope.zeroNorth());
    }
    
}
