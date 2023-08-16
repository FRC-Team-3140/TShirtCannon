// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LightEmittingDiode extends SubsystemBase {
    private static AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(101);
    private static AddressableLED led;
    private static int m_rainbowFirstPixelHue = 0;

    public LightEmittingDiode(){
        led = new AddressableLED(0);
        led.setLength(ledBuffer.getLength());
        led.start();
    }

    public void flash(int numOfFlashes){
        for(int i = 0; i < numOfFlashes; i++){
            setLedColorSolid(255, 255, 255);

            Timer.delay(0.2);

            setLedColorSolid(0, 0, 0);
        }
    }

    public void rainbow(){
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            int hue;
            if (i<55){
                hue = (m_rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180; 
                ledBuffer.setHSV(i, hue, 255, 128);
            }
            else
            {
                hue=0;
                if ((m_rainbowFirstPixelHue%20)<10)
                {
                    ledBuffer.setHSV(i, hue, 255, 0);
                }
                else
                {
                    ledBuffer.setHSV(i, hue, 255, 128);
                }
        
            }
        }
        m_rainbowFirstPixelHue += 3;
        m_rainbowFirstPixelHue %= 180;
        led.setData(ledBuffer);
    }



    public static void setLedColorSolid(int R, int G, int B){
        for(int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setRGB(i, R, G, B);
        }
        led.setData(ledBuffer);
    }
}
