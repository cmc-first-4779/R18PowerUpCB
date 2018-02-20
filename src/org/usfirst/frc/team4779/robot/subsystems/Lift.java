package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.lift.LiftOff;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  The Lift Subsystem not only controls the "elevator" lift system, but also the Climber.
 */

public class Lift extends PIDSubsystem {
	//  Declare our Spark Motor that powers the lift
	Spark liftMotor = new Spark(RobotMap.liftMotorPWMPort);
	private static Encoder liftEncoder = new Encoder(RobotMap.liftEncoderChannelA, RobotMap.liftEncoderChannelB);
	//private AnalogInput rangefinder = new AnalogInput(0);
	
	
	public Lift() {
		 super("Lift", RobotMap.liftPValue, RobotMap.liftIValue, RobotMap.liftDValue);
		 liftEncoder.setDistancePerPulse(RobotMap.liftDistancePerPulse);
		 setAbsoluteTolerance(RobotMap.liftTolerance);
		// SmartDashboard.putData("Lift State:  ", Robot.lift);
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
    	if (getDistance() <= 15)  {
    		liftMotor.set(RobotMap.liftThrottleDown);
    	}else {
    		liftMotor.set(RobotMap.liftMotorPowerDown);
    	}
    }
    
    
    public void liftOff() {
    	//  Power the Lift Off.
    	liftMotor.set(RobotMap.liftMotorPowerOff);	
    }
    
    public void liftMove(double power) {
    	SmartDashboard.putNumber("Lift Encoder Position:  ", getDistance());
    	SmartDashboard.putNumber("Lift Power", power);
    	if ((Robot.lift.getDistance() < RobotMap.liftThrottleHeight))   {
    		liftMotor.set(power);
    	}
    	else if (Robot.lift.getSetpoint() < Robot.lift.getDistance()) {
    		liftMotor.set(power);
    	}	
    	else  { 
    		liftMotor.set(power*RobotMap.liftThrottleDown);
    	}
    }
    
	public void log() {
	}

	public static double getDistance() {
		return liftEncoder.getDistance();
	}
	
	public void resetLiftEncoder()  {
		liftEncoder.reset();
	}
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return liftEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		liftMove(output);	
		SmartDashboard.putNumber("Lift Encoder Distance: ", Robot.lift.getDistance());
	}
}




