// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.AddressableLED;
import frc.robot.RobotContainer;

public class LightEmittingDiode {
    private static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(95); // 32 LED's in Top | 33 LED's Around Cannon | 83 LED's on the bottom
    private static AddressableLED led;
    private static final int[] Top = {84, 95}; // Last 12 LEDs
    private static final int[] Mid = {52, 83}; // Middle 32
    private static final int[] Bottom = {0, 51}; // First 51 
    
    
    //min and max LED buffer index for each section
    private static final int[][] LEDsections = { Top, Mid, Bottom };

    public LightEmittingDiode(){
        led = new AddressableLED(0);
        led.setLength(ledBuffer.getLength());
        led.start();
    }

    public void flash(int numOfFlashes){
        for(int i = 0; i < numOfFlashes; i++){
            setLedColorSolid(255, 255, 255);

            Timer.delay(0.1);

            setLedColorSolid(0, 0, 0);

            if(numOfFlashes > 1){
                Timer.delay(Pneumatics.salvoDelayTime);
            }
        }
    }

    public void rainbow(){
        //Middle and top Sections Rainbow
        for (var i = LEDsections[1][0]; i < LEDsections[0][1]; i++) {
            ledBuffer.setRGB(i, RobotContainer.getRandomInt(0, 255), RobotContainer.getRandomInt(0, 255), RobotContainer.getRandomInt(0, 255));
        }
        led.setData(ledBuffer);
    }

    public void water(){
        //runs on Bottom Section only
        for (var i = LEDsections[2][0]; i < LEDsections[2][1]; i++){
            ledBuffer.setRGB(i, 0, 0, RobotContainer.getRandomInt(0, 255));
        }
        led.setData(ledBuffer);
    }

    public void colorRampUp(int R, int G, int B, double duration, boolean rumble){
        /***************************************************************/
        /* This method takes in a color and ramps up to that color over*/
        /* a duration.                                                 */
        /***************************************************************/
        int currentR = 0;
        int currentG = 0;
        int currentB = 0;
        double currentRumble = 0;
        
        for (int i = 0; i < 255; i++){
            if(i < R){
                currentR = i;
            } else {
                currentR = R;
            }

            if(i < G){
                currentG = i;
            } else {
                currentG = G;
            }

            if(i < B){
                currentB = i;
            } else {
                currentB = B;
            }
            setLedColorSolid(currentR, currentG, currentB);

            if(rumble){
                currentRumble = i / 255;
            }

            RobotContainer.m_controller.setRumble(RumbleType.kBothRumble, currentRumble);
         
            Timer.delay(duration - 0.02);
        }

    }

    public void setLedColorSolid(int R, int G, int B){
        for(int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setRGB(i, R, G, B);
        }
        led.setData(ledBuffer);
    }
}
