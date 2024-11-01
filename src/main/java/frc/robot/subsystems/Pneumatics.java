package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Timer;

public class Pneumatics extends SubsystemBase {
    private static Pneumatics instance = null;

    public static Pneumatics getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new Pneumatics();
        return instance;
    }

    private Pneumatics() {
        closeAll();
    }

    // Locations are declared as looking at the bot from behind
    public static Solenoid leftSolenoid = new Solenoid(4, PneumaticsModuleType.CTREPCM, 0);
    public static Solenoid midSolenoid = new Solenoid(4, PneumaticsModuleType.CTREPCM, 2);
    public static Solenoid rightSolenoid = new Solenoid(4, PneumaticsModuleType.CTREPCM, 1);

    // Delay Times
    public final static double valveDelayTime = 0.04;
    public final static double salvoDelayTime = 0.25;

    public void closeAll() {
        leftSolenoid.set(false);
        midSolenoid.set(false);
        rightSolenoid.set(false);
    }

    public void fireLeft() {
        new ParallelCommandGroup(
                new InstantCommand(() -> RobotContainer.led.flash()),
                new InstantCommand(() -> {
                    leftSolenoid.set(true);
                    Timer.delay(valveDelayTime);
                    leftSolenoid.set(false);
                }),
                new WaitCommand(salvoDelayTime)).schedule();
    }

    public void fireMid() {
        new ParallelCommandGroup(
                new InstantCommand(() -> RobotContainer.led.flash()),
                new InstantCommand(() -> {
                    midSolenoid.set(true);
                    // slight delay
                    Timer.delay(valveDelayTime);
                    midSolenoid.set(false);
                })).schedule();
    }

    public void fireRight() {
        new ParallelCommandGroup(
                new InstantCommand(() -> RobotContainer.led.flash()),
                new InstantCommand(() -> {
                    rightSolenoid.set(true);
                    // slight delay
                    Timer.delay(valveDelayTime);
                    rightSolenoid.set(false);
                })).schedule();
    }

    public void fireSalvo() {
        new SequentialCommandGroup(
                new ParallelCommandGroup(
                        new InstantCommand(() -> RobotContainer.led.flash()),
                        new InstantCommand(() -> {
                            leftSolenoid.set(true);
                            // slight delay
                            Timer.delay(valveDelayTime);
                            leftSolenoid.set(false);
                        })),

                new WaitCommand(salvoDelayTime),

                new ParallelCommandGroup(
                        new InstantCommand(() -> RobotContainer.led.flash()),
                        new InstantCommand(() -> {
                            midSolenoid.set(true);
                            // slight delay
                            Timer.delay(valveDelayTime);
                            midSolenoid.set(false);
                        })),

                new WaitCommand(salvoDelayTime),

                new ParallelCommandGroup(
                        new InstantCommand(() -> RobotContainer.led.flash()),
                        new InstantCommand(() -> {
                            rightSolenoid.set(true);
                            // slight delay
                            Timer.delay(valveDelayTime);
                            rightSolenoid.set(false);
                        })),

                new WaitCommand(salvoDelayTime),

                new InstantCommand(() -> closeAll())).schedule();
    }
}
