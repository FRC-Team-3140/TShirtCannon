package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class tankdrive extends Command {
    DriveTrain driveTrain;

    public tankdrive(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.tankdrive(RobotContainer.m_controller.getLeftY(), RobotContainer.m_controller.getRightY());
    }
}
