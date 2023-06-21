package org.team555;

import org.team555.components.managers.ManagedGyroscope;
import org.team555.components.subsystems.Drivetrain;
import org.team555.constants.ControllerConstants;
import org.team555.inputs.JoystickInput;
import org.team555.util.frc.GameController;
import org.team555.util.frc.GameController.Axis;
import org.team555.util.frc.GameController.Button;
import org.team555.util.frc.commandrobot.RobotContainer;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Commands;

public class ChargedUp extends RobotContainer {
    public static ManagedGyroscope gyroscope = new ManagedGyroscope();
    public static GameController driverController = GameController.from(ControllerConstants.DRIVER_CONTROLLER_TYPE, ControllerConstants.DRIVER_CONTROLLER_PORT);
    public static GameController operatorController = GameController.from(ControllerConstants.OPERATOR_CONTROLLER_TYPE, ControllerConstants.OPERATOR_CONTROLLER_PORT);
    public static Drivetrain drivetrain = new Drivetrain();

    @Override
    public void initialize() {
        driverController.getButton(Button.START_TOUCHPAD).onTrue(Commands555.zeroNAVX());

        drivetrain.setDefaultCommand(Commands.run(
            () -> {
                JoystickInput drive = JoystickInput.getLeft(driverController, false, true);
                JoystickInput turn = JoystickInput.getRight(driverController, false, true);

                drivetrain.driveFromInputs(turn, drive);
            },
            drivetrain
        ));

    }

   

    
}
