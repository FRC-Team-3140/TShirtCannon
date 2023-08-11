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

    // Delay Times
    private double valveDelayTime = 0.025;
    private double salvoDelayTime = 0.25;

    public void closeAll(){
        leftSolenoid.set(false);
        midSolenoid.set(false);
        rightSolenoid.set(false);
    }
    public void fireLeft(){
        leftSolenoid.set(true);
        //slight delay 
        Timer.delay(valveDelayTime);
        leftSolenoid.set(false);
    }
    public void fireMid(){
        midSolenoid.set(true);
        //slight delay 
        Timer.delay(valveDelayTime);

        midSolenoid.set(false);
    }
    public void fireRight(){
        rightSolenoid.set(true);
        //slight delay 
        Timer.delay(valveDelayTime);
        rightSolenoid.set(false);
    }
    public void fireSalvo(){
        leftSolenoid.set(true);
        //slight delay 
        Timer.delay(valveDelayTime);
        leftSolenoid.set(false);

        Timer.delay(salvoDelayTime);

        midSolenoid.set(true);
        //slight delay 
        Timer.delay(valveDelayTime);
        midSolenoid.set(false);

        Timer.delay(salvoDelayTime);

        rightSolenoid.set(true);
        //slight delay 
        Timer.delay(valveDelayTime);
        rightSolenoid.set(false);

        closeAll();
    }
}
