// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.arcadedrive;
import frc.robot.commands.tankdrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;

public class RobotContainer {
  public RobotContainer() {
    configureBindings();
  }
  static Pneumatics pneumatics = new Pneumatics();
  DriveTrain driveTrain = new DriveTrain();
  public static XboxController test = new XboxController(0);
  private void configureBindings() {
    driveTrain.setDefaultCommand(new arcadedrive(driveTrain));
    new JoystickButton(test, Button.kX.value).onTrue(new InstantCommand(() -> pneumatics.fireLeft()));
    new JoystickButton(test, Button.kB.value).onTrue(new InstantCommand(() -> pneumatics.fireRight()));
    new JoystickButton(test, Button.kA.value).onTrue(new InstantCommand(() -> pneumatics.fireMid()));
    new JoystickButton(test, Button.kY.value).onTrue(new InstantCommand(() -> pneumatics.closeAll()));
  }
  
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
