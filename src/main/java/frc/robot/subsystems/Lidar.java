// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.XboxController;

public class Lidar extends SubsystemBase {
  /** Creates a new Lidar. */
  private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
  private final NetworkTable sensor = inst.getTable("rplidar");

  private static double min_dist;
  private static final double minDistCutoff = (2.5); // RPLidar measures in mm
  private static final XboxController controller = RobotContainer.m_controller;
  private static boolean Override = false;

  public Lidar() {
  }

  @Override
  public void periodic() {
    if (!Override) {
      min_dist = sensor.getSubTable("rplidar").getEntry("roi_min_distance").getDouble(0.0);
      sensor.getEntry("Overridden").setBoolean(Override);
      sensor.getEntry("getFireAllow()").setBoolean(getFireAllow());
      sensor.getEntry("roi_min_distance").setDouble(min_dist);

      if (controller.getRightStickButtonPressed()) {
        Override = true;
      }
    }
  }

  public boolean getFireAllow() {
    if (min_dist > minDistCutoff && Override == false) {
      return true;
    } else if (Override == true) {
      return true;
    } else {
      RobotContainer.led.setLedColorSolid(255, 255, 0);
      return false;
    }
  }

  public boolean getOverridden() {
    return Override;
  }
}
