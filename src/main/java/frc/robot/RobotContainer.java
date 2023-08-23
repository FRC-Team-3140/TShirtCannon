// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.arcadedrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LightEmittingDiode;
import frc.robot.subsystems.Pneumatics;

public class RobotContainer {
  public RobotContainer() {
    configureBindings();
  }
  static Pneumatics pneumatics = new Pneumatics();
  DriveTrain driveTrain = new DriveTrain();
  public static XboxController m_controller = new XboxController(0);
  private void configureBindings() {
    driveTrain.setDefaultCommand(new arcadedrive(driveTrain));
  }

  public static void controlls(){
    if(m_controller.getLeftBumper()) {
      LightEmittingDiode.setLedColorSolid(255, 0, 0);
      // new JoystickButton(m_controller, Button.kX.value).onTrue(new InstantCommand(() -> pneumatics.fireLeft()));
      // new JoystickButton(m_controller, Button.kB.value).onTrue(new InstantCommand(() -> pneumatics.fireRight()));
      // new JoystickButton(m_controller, Button.kA.value).onTrue(new InstantCommand(() -> pneumatics.fireMid()));
      // new JoystickButton(m_controller, Button.kY.value).onTrue(new InstantCommand(() -> pneumatics.fireSalvo())); 
      if(m_controller.getXButton()){
        pneumatics.fireLeft();
      } else if(m_controller.getBButton()){
        pneumatics.fireRight();
      } else if(m_controller.getAButton()){
        pneumatics.fireMid();
      } else if(m_controller.getYButton()) {
        pneumatics.fireSalvo();
      }
    } else {
      LightEmittingDiode.rainbow();
      LightEmittingDiode.water();
    }
  }
  
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
