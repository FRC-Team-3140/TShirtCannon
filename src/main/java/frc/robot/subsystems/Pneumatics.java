package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
public class Pneumatics extends SubsystemBase {
    public Pneumatics(){}
    //Locations are declared as looking at the bot from behind
    public Solenoid leftSolenoid = new Solenoid(4, PneumaticsModuleType.CTREPCM, 0);
    public Solenoid midSolenoid = new Solenoid(4, PneumaticsModuleType.CTREPCM, 2);
    public Solenoid rightSolenoid = new Solenoid(4, PneumaticsModuleType.CTREPCM, 1);
    public void closeAll(){
        leftSolenoid.set(false);
        midSolenoid.set(false);
        rightSolenoid.set(false);
    }
    public void fireLeft(){
        leftSolenoid.set(true);
        //slight delay 
        Timer.delay(.025);
        leftSolenoid.set(false);
    }
    public void fireMid(){
        midSolenoid.set(true);
        //slight delay 
        Timer.delay(.025);

        midSolenoid.set(false);
    }
    public void fireRight(){
        rightSolenoid.set(true);
        //slight delay 
        Timer.delay(.025);
        rightSolenoid.set(false);
    }

    
    
}
