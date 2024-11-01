// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LightEmittingDiode {
    private static LightEmittingDiode instance = null;

    private static final NetworkTableInstance inst = NetworkTableInstance.getDefault();

    private NetworkTableEntry modeTable = inst.getTable("LEDControl").getEntry("mode");

    private NetworkTableEntry waterMode = inst.getTable("LEDControl").getEntry("waterMode"); 

    private NetworkTableEntry eventTime = inst.getTable("LEDControl").getEntry("eventTime"); 

    private int mode = 0;

    private NetworkTableEntry R = inst.getTable("LEDControl").getEntry("R");
    private NetworkTableEntry G = inst.getTable("LEDControl").getEntry("G");
    private NetworkTableEntry B = inst.getTable("LEDControl").getEntry("B");

    public enum defaultWaterMode {
        NOISE("Noise"),
        SINE("Sine"),
        RANDOM("Random");
    
        private final String modeName;
    
        defaultWaterMode(String modeName) {
            this.modeName = modeName;
        }
    
        public String getModeName() {
            return modeName;
        }
    }
    
    public static LightEmittingDiode getInstance() {
        if (instance == null) {
            instance = new LightEmittingDiode();
        }
        return instance;
    }

    private LightEmittingDiode() {
        waterMode.setString(defaultWaterMode.RANDOM.getModeName());

        modeTable.setInteger(mode);

        new Thread(() -> updateTime()).start();

        R.setInteger(255);
        G.setInteger(255);
        B.setInteger(255);
    }

    private void updateTime() {
        while (true) {
            inst.getTable("LEDControl").getEntry("timestamp").setDouble(Timer.getFPGATimestamp());
        }
    }

    public void setDefault(defaultWaterMode mode) {
        this.mode = 0;

        modeTable.setInteger(this.mode);

        waterMode.setString(mode.getModeName());
    }

    public void error() {
        mode = 1;

        modeTable.setInteger(mode);
    }

    public void setLedColorSolid(int R, int G, int B) {
        mode = 2;

        modeTable.setInteger(this.mode);

        this.R.setInteger(R);
        this.G.setInteger(G);
        this.B.setInteger(B);
    }

    public void flash() {
        mode = 3;

        eventTime.setDouble(Timer.getFPGATimestamp() + 1); // Current time plus 1 second - TK
    }

    public void colorRampUp(int R, int G, int B, double duration, boolean rumble) {
        // /***************************************************************/
        // /* This method takes in a color and ramps up to that color over */
        // /* a duration. */
        // /***************************************************************/
        // int currentR = 0;
        // int currentG = 0;
        // int currentB = 0;
        // double currentRumble = 0;

        // for (int i = 0; i < 255; i++) {
        // if (i < R) {
        // currentR = i;
        // } else {
        // currentR = R;
        // }

        // if (i < G) {
        // currentG = i;
        // } else {
        // currentG = G;
        // }

        // if (i < B) {
        // currentB = i;
        // } else {
        // currentB = B;
        // }
        // setLedColorSolid(currentR, currentG, currentB);

        // if (rumble) {
        // currentRumble = i / 255;
        // }

        // RobotContainer.m_controller.setRumble(RumbleType.kBothRumble, currentRumble);

        // Timer.delay(duration - 0.02);
        // }
    }
}
