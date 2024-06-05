package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private double deadzone = 0.1;

    // Left side motors need to be inverted
    TalonSRX flSrx = new TalonSRX(6);
    TalonSRX frSrx = new TalonSRX(3);
    TalonSRX blSrx = new TalonSRX(8);
    TalonSRX brSrx = new TalonSRX(2);

    public DriveTrain() {
        setIdleModes();
        blSrx.follow(flSrx);
        brSrx.follow(frSrx);
    }

    private void setIdleModes() {
        flSrx.setNeutralMode(NeutralMode.Brake);
        frSrx.setNeutralMode(NeutralMode.Brake);
        blSrx.setNeutralMode(NeutralMode.Brake);
        brSrx.setNeutralMode(NeutralMode.Brake);
    }

    public void tankdrive(double leftPercent, double rightPercent) {
        flSrx.set(ControlMode.PercentOutput, -leftPercent);
        frSrx.set(ControlMode.PercentOutput, rightPercent);
    }

    public void arcadedrive(double x, double y) {
        if (Math.abs(x) > deadzone || Math.abs(y) > deadzone) {
            flSrx.set(ControlMode.PercentOutput, -(y - x));
            frSrx.set(ControlMode.PercentOutput, (x + y));
        } else {
            flSrx.set(ControlMode.PercentOutput, 0);
            frSrx.set(ControlMode.PercentOutput, 0);
        }
    }
}
