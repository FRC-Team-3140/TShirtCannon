// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Controls extends SubsystemBase {
  private static Controls instance = null;

  private static boolean leftFired = false;
  private static boolean rightFired = false;
  private static boolean middleFired = false;
  private static boolean salvoFired = false;

  public static Controls getInstance() {
    if (instance != null) {
      return instance;
    }
    instance = new Controls();
    return instance;
  }

  /** Creates a new Controls. */
  private Controls() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if ((RobotContainer.m_controller.getLeftBumper()) && (RobotContainer.m_lidar.getFireAllow())) {
      if (RobotContainer.m_controller.getXButtonPressed() && !leftFired) {
        RobotContainer.pneumatics.fireLeft();
        leftFired = true;
      } else if (RobotContainer.m_controller.getBButtonPressed() && !rightFired) {
        RobotContainer.pneumatics.fireRight();
        rightFired = true;
      } else if (RobotContainer.m_controller.getAButtonPressed() && !middleFired) {
        RobotContainer.pneumatics.fireMid();
        middleFired = true;
      } else if (RobotContainer.m_controller.getYButtonPressed() && !salvoFired) {
        RobotContainer.pneumatics.fireSalvo();
        salvoFired = true;
      } else {
        RobotContainer.led.setLedColorSolid(255, 0, 0);
        leftFired = false;
        rightFired = false;
        middleFired = false;
        salvoFired = false;
      }
    } else {
      if (RobotContainer.m_lidar.getFireAllow()) {
        RobotContainer.led.rainbow();
        RobotContainer.led.water();
      }
    }
  }
}
