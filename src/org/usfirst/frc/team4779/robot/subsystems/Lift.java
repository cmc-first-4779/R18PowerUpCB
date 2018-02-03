package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.ClimberOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
	Spark liftMotor = new Spark(RobotMap.liftMotor);
	
	public Lift() {
		 super();
	 }
	
	public void initDefaultCommand() {
    	setDefaultCommand(new LiftOff());    
    }
    
    public void climberOn() {
    	climbMotor.set(1);	
    }
    public void climberOff() {
    	climbMotor.set(0);	
    }
    
	public void log() {
	}
}



    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

