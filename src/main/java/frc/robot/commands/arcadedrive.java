package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class arcadedrive extends CommandBase {
    DriveTrain driveTrain;
    public arcadedrive(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }
    @Override
    public void execute() {
        
        driveTrain.arcadedrive(RobotContainer.test.getRightX(), RobotContainer.test.getLeftY());
    }
}