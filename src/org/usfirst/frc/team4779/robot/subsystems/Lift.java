package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.LiftOff;

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
    
    public void liftUp() {
    	liftMotor.set(RobotMap.liftMotorPowerUp);	
    }
    
    public void liftUpTurbo() {
    	liftMotor.set(RobotMap.liftMotorPowerTurbo);	
    }
    
    public void liftDown() {
    	liftMotor.set(RobotMap.liftMotorPowerDown);	
    }
    
    
    public void liftOff() {
    	liftMotor.set(RobotMap.liftMotorPowerOff);	
    }
    
	public void log() {
	}
}




