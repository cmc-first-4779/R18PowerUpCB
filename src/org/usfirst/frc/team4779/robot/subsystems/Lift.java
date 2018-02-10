package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.lift.LiftOff;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  The Lift Subsystem not only controls the "elevator" lift system, but also the Climber.
 */

public class Lift extends Subsystem {
	//  Declare our Spark Motor that powers the lift
	Spark liftMotor = new Spark(RobotMap.liftMotor);
	private Encoder liftEncoder = new Encoder(0, 1);
	private AnalogInput rangefinder = new AnalogInput(0);
	
	
	public Lift() {
		 super("Lift");
		 liftEncoder.setDistancePerPulse(.005);
	 }
	
	//   By default, we want the Lift Off to not drain the battery when its not being called.
	public void initDefaultCommand() {
    	setDefaultCommand(new LiftOff());    
    }
    
    public void liftUp() {
    	//  Move the Lift up.
    	SmartDashboard.putNumber("Lift Distance", liftEncoder.getDistance());
    	liftMotor.set(RobotMap.liftMotorPowerUp);	
    }
    
    public void liftUpTurbo() {
    	//  Move the Lift Up REALLY FAST.  (Turbo)
    	liftMotor.set(RobotMap.liftMotorPowerTurbo);	
    }
    
    public void liftDown() {
    	//  Move the Lift Down.
    	SmartDashboard.putNumber("Lift Distance", liftEncoder.getDistance());
    	liftMotor.set(RobotMap.liftMotorPowerDown);	
    }
    
    
    public void liftOff() {
    	//  Power the Lift Off.
    	liftMotor.set(RobotMap.liftMotorPowerOff);	
    }
    
    public void liftMove(double power) {
    	SmartDashboard.putNumber("Proximity: ", rangefinder.getAverageVoltage());
    	SmartDashboard.putNumber("Lift Power", power);
    	liftMotor.set(power);
    }
    
	public void log() {
	}

	public double getPosition() {
		
		return rangefinder.getAverageVoltage();
	}
}




