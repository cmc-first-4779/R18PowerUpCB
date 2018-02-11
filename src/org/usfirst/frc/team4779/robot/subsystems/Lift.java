package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.lift.LiftOff;


import edu.wpi.first.wpilibj.command.PIDSubsystem;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  The Lift Subsystem not only controls the "elevator" lift system, but also the Climber.
 */

public class Lift extends PIDSubsystem {
	
	
	public Lift() {
		 super("Lift", 0.1, 0, 0); // PID VALUES.  NEED TO TUNE THESE
		 
		 //SET THE DistancePerPulse.
		 RobotMap.liftEncoder.setDistancePerPulse(RobotMap.liftDistancePerPulse);
	 }
	

	public void initDefaultCommand() {
		//   By default, we want the Lift Off to not drain the battery when its not being called.
    	setDefaultCommand(new LiftOff());    
    }
    
    public void liftUp() {
    	//  Move the Lift up.
    	SmartDashboard.putNumber("Lift Distance", RobotMap.liftEncoder.getDistance());
    	RobotMap.liftMotor.set(RobotMap.liftMotorPowerUp);	
    }
    
    public void liftUpTurbo() {
    	//  Move the Lift Up REALLY FAST.  (Turbo)
    	RobotMap.liftMotor.set(RobotMap.liftMotorPowerTurbo);	
    }
    
    public void liftDown() {
    	//  Move the Lift Down.
    	SmartDashboard.putNumber("Lift Distance", RobotMap.liftEncoder.getDistance());
    	RobotMap.liftMotor.set(RobotMap.liftMotorPowerDown);	
    }
    
    
    public void liftOff() {
    	//  Power the Lift Off.
    	RobotMap.liftMotor.set(RobotMap.liftMotorPowerOff);	
    }
    
    public void liftMove(double power) {
    	SmartDashboard.putNumber("Lift Distance: ", RobotMap.liftEncoder.getDistance());
    	SmartDashboard.putNumber("Lift Power", power);
    	RobotMap.liftMotor.set(power);
    }
    
	public void log() {
	}

	public double getDistance() {
		//Get the distance that the liftEncoder is reading and return it.
		return RobotMap.liftEncoder.getDistance();
	}

	@Override
	protected double returnPIDInput() {
		//  Our Current PID Input is the Distance that the LIFT Encoder is reading.
		return RobotMap.liftEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		//  Our PID OUTPUT is to move the Lift up/down by the output of the PID.
		liftMove(output);	
		
	}
}




