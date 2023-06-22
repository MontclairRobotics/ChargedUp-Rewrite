package org.team555.components.subsystems;

import static org.team555.constants.ElevatorConstants.*;

import org.team555.util.frc.PIDMechanism;
import org.team555.util.frc.commandrobot.ManagerSubsystemBase;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;

public class Elevator extends ManagerSubsystemBase {
    private PIDController heightController;
    private CANSparkMax motor;
    private PIDMechanism heightMechanism;

    public Elevator() {
        heightController = new PIDController(KP, KI, KD);
        heightMechanism = new PIDMechanism(heightController);        
    }


    @Override
    public void always() {

    }
}
