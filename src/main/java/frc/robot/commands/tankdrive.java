package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class tankdrive extends CommandBase {
    DriveTrain driveTrain;
    public tankdrive(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }
    @Override
    public void execute() {
        
        driveTrain.tankdrive(RobotContainer.test.getLeftY(), RobotContainer.test.getRightY());
    }
}

