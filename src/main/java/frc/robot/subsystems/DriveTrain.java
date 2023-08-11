package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    //I didn't implement a deadband because there already seems to be a natural deadband due to friction
    //Also, the left side motors need to be inverted
    TalonSRX flSrx = new TalonSRX(6);
    TalonSRX blSrx = new TalonSRX(8);
    TalonSRX frSrx = new TalonSRX(3); 
    TalonSRX brSrx = new TalonSRX(2);
    public DriveTrain(){
        setIdleModes();
        blSrx.follow(flSrx);
        brSrx.follow(frSrx);
    }
    private void setIdleModes(){
        flSrx.setNeutralMode(NeutralMode.Brake);
        frSrx.setNeutralMode(NeutralMode.Brake);
        blSrx.setNeutralMode(NeutralMode.Brake);
        brSrx.setNeutralMode(NeutralMode.Brake);
    }
    public void tankdrive(double leftPercent, double rightPercent){
        flSrx.set(ControlMode.PercentOutput, -leftPercent);
        frSrx.set(ControlMode.PercentOutput, rightPercent);
    }
    public void arcadedrive(double y, double x){
        flSrx.set(ControlMode.PercentOutput,-( y + x));
        frSrx.set(ControlMode.PercentOutput, x - y);
    }
}
