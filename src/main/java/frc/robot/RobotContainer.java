// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.arcadedrive;
import frc.robot.subsystems.Controls;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lidar;
import frc.robot.subsystems.LightEmittingDiode;
import frc.robot.subsystems.Pneumatics;

public class RobotContainer {
  public RobotContainer() {
    configureBindings();
  }

  public static Pneumatics pneumatics = Pneumatics.getInstance();
  public static LightEmittingDiode led = LightEmittingDiode.getInstance();
  public static DriveTrain driveTrain = DriveTrain.getInstance();
  public static Controls controls = Controls.getInstance();
  public static XboxController m_controller = new XboxController(0);
  public static Lidar m_lidar = new Lidar();

  private void configureBindings() {
    driveTrain.setDefaultCommand(new arcadedrive(driveTrain));

    // new JoystickButton(RobotContainer.m_controller, Button.kLeftBumper.value).whileTrue(new InstantCommand(() -> led.setLedColorSolid(255, 0, 0)));
  }

  public static void controls() {
  }

  public static int getRandomInt(int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    return (int) (Math.random() * ((max - min) + 1) + min);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
