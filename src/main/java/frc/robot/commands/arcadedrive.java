package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class arcadedrive extends Command {
    DriveTrain driveTrain;

    public arcadedrive(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.arcadedrive(RobotContainer.m_controller.getRightX(), RobotContainer.m_controller.getLeftY());
    }
}