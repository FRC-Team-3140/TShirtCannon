package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private static DriveTrain instance = null; 
    
    // private double deadzone = 0.1;

    private final DifferentialDrive m_Drive; 

    // Left side motors need to be inverted
    WPI_TalonSRX flSrx = new WPI_TalonSRX(6);
    WPI_TalonSRX frSrx = new WPI_TalonSRX(3);
    WPI_TalonSRX blSrx = new WPI_TalonSRX(8);
    WPI_TalonSRX brSrx = new WPI_TalonSRX(2);
    WPI_TalonSRX trSrx = new WPI_TalonSRX(4);
    WPI_TalonSRX tlSrx = new WPI_TalonSRX(5);

    public static DriveTrain getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new DriveTrain();
        return instance;
    }

    private DriveTrain() {
        factoryReset(); 
        setIdleModes();
        blSrx.follow(flSrx);
        tlSrx.follow(flSrx);
        brSrx.follow(frSrx);
        trSrx.follow(frSrx);

        m_Drive = new DifferentialDrive(flSrx, frSrx);
    }

    private void factoryReset() {
        flSrx.configFactoryDefault();
        frSrx.configFactoryDefault();
        tlSrx.configFactoryDefault();
        trSrx.configFactoryDefault();
        blSrx.configFactoryDefault();
        brSrx.configFactoryDefault();
    }

    private void setIdleModes() {
        flSrx.setNeutralMode(NeutralMode.Brake);
        frSrx.setNeutralMode(NeutralMode.Brake);
        tlSrx.setNeutralMode(NeutralMode.Brake);
        trSrx.setNeutralMode(NeutralMode.Brake);
        blSrx.setNeutralMode(NeutralMode.Brake);
        brSrx.setNeutralMode(NeutralMode.Brake);
    }

    // public void tankdrive(double leftPercent, double rightPercent) {
    //     flSrx.set(ControlMode.PercentOutput, -leftPercent);
    //     frSrx.set(ControlMode.PercentOutput, rightPercent);
    // }

    public void arcadedrive(double x, double y) { 
        // if (Math.abs(x) > deadzone || Math.abs(y) > deadzone) {
        //     flSrx.set(ControlMode.PercentOutput, -(y - x));
        //     frSrx.set(ControlMode.PercentOutput, (x + y));
        // } else {
        //     flSrx.set(ControlMode.PercentOutput, 0);
        //     frSrx.set(ControlMode.PercentOutput, 0);
        // }
        m_Drive.arcadeDrive(x, y);
    }
}
