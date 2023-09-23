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
  private static final double minDistCutoff = (1 * 1000); // RPLidar measures in mm
  private static final XboxController controller = RobotContainer.m_controller;
  private static boolean Override = false; 

  public Lidar() {}

  @Override
  public void periodic() {
    min_dist = sensor.getValue("min_distance").getDouble();
    if(controller.getRightStickButtonPressed()){
      Override = true;
    }
  }

  public static boolean getFireAllow() {
    if(min_dist > minDistCutoff){
      return true;
    } else {
      return false; 
    }
  }

  public static boolean getOverridden(){
    return Override;
  }
}
