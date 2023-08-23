// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.lang.Math;

public class LightEmittingDiode extends SubsystemBase {
    private static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(148); // 32 LED's in Top | 33 LED's Around Cannon | 83 LED's on the bottom
    private static AddressableLED led;
    private static final int[] Top = {116, 148};
    private static final int[] Mid = {83 ,115};
    private static final int[] Bottom = {0 ,82};

    //min and max LED buffer index for each section
    private static final int[][] LEDsections = { Top, Mid, Bottom };

    public LightEmittingDiode(){
        led = new AddressableLED(0);
        led.setLength(ledBuffer.getLength());
        led.start();
    }

    public static void flash(int numOfFlashes){
        for(int i = 0; i < numOfFlashes; i++){
            setLedColorSolid(255, 255, 255);

            Timer.delay(0.2);

            setLedColorSolid(0, 0, 0);
        }
    }

    public static void rainbow(){
        //Middle and top Sections Rainbow
        for (var i = LEDsections[1][0]; i < LEDsections[0][1]; i++) {
            ledBuffer.setRGB(i, (int)(Math.random()*255)%255, (int)(Math.random()*255)%255, (int)(Math.random()*255)%255);
        }
        led.setData(ledBuffer);
    }



    public static void setLedColorSolid(int R, int G, int B){
        for(int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setRGB(i, R, G, B);
        }
        led.setData(ledBuffer);
    }
}
