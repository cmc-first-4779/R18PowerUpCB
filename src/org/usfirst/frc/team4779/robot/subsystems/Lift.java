package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.LiftOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  The Lift Subsystem not only controls the "elevator" lift system, but also the Climber.
 */

public class Lift extends Subsystem {
	//  Declare our Spark Motor that powers the lift
	Spark liftMotor = new Spark(RobotMap.liftMotor);
	
	public Lift() {
		 super();
	 }
	
	//   By default, we want the Lift Off to not drain the battery when its not being called.
	public void initDefaultCommand() {
    	setDefaultCommand(new LiftOff());    
    }
    
    public void liftUp() {
    	//  Move the Lift up.
    	liftMotor.set(RobotMap.liftMotorPowerUp);	
    }
    
    public void liftUpTurbo() {
    	//  Move the Lift Up REALLY FAST.  (Turbo)
    	liftMotor.set(RobotMap.liftMotorPowerTurbo);	
    }
    
    public void liftDown() {
    	//  Move the Lift Down.
    	liftMotor.set(RobotMap.liftMotorPowerDown);	
    }
    
    
    public void liftOff() {
    	//  Power the Lift Off.
    	liftMotor.set(RobotMap.liftMotorPowerOff);	
    }
    
	public void log() {
	}
}




