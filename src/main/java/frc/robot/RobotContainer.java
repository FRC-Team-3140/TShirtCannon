// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.arcadedrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lidar;
import frc.robot.subsystems.LightEmittingDiode;
import frc.robot.subsystems.Pneumatics;

public class RobotContainer {
  public RobotContainer() {
    configureBindings();
  }

  public static Pneumatics pneumatics = new Pneumatics();
  public static LightEmittingDiode led = new LightEmittingDiode();
  public static DriveTrain driveTrain = new DriveTrain();
  public static XboxController m_controller = new XboxController(0);

  private static boolean hasRun = false;
  private static boolean leftFired = false;
  private static boolean rightFired = false;
  private static boolean middleFired = false;
  private static boolean salvoFired = false;

  private void configureBindings() {
    driveTrain.setDefaultCommand(new arcadedrive(driveTrain));
  }

  public static void controlls(){
    if(m_controller.getLeftBumper() && Lidar.getFireAllow()) {
      if(!hasRun) {
        led.setLedColorSolid(255, 0, 0);
        hasRun = true;
      }
      if(m_controller.getXButtonPressed() && !leftFired){
        pneumatics.fireLeft();
        leftFired = true;
      } else if(m_controller.getBButtonPressed() && !rightFired){
        pneumatics.fireRight();
        rightFired = true;
      } else if(m_controller.getAButtonPressed() && !middleFired){
        pneumatics.fireMid();
        middleFired = true;
      } else if(m_controller.getYButtonPressed() && !salvoFired) {
        pneumatics.fireSalvo();
        salvoFired = true;
      } else {
        leftFired = false;
        rightFired = false;
        middleFired = false;
        salvoFired = false;
      }
    } else {
      hasRun = false;
      led.rainbow();
      led.water();
    }
  }

  public static int getRandomInt(int min, int max){
    if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

    return (int)(Math.random() * ((max-min)+1) + min);
  }
  
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
