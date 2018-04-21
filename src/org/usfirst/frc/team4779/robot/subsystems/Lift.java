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
	
	//  Declare and initiate our Lift encoder.
	private static Encoder liftEncoder = new Encoder(RobotMap.liftEncoderChannelA, RobotMap.liftEncoderChannelB);
	
	
	public Lift() {
		 super("Lift", RobotMap.liftPValue, RobotMap.liftIValue, RobotMap.liftDValue);
		 
		//Set the distance per pulse per Rotary Encoder.    Got this through calibration and testing.
			//Check to see if we are using the Mule robot, and if so, use it's distance per pulse.  Otherwise, use CuBert's value
		 setEncoderDistancePerPulse();
		 
		 //liftEncoder.setDistancePerPulse(RobotMap.liftDistancePerPulse);
		 setAbsoluteTolerance(RobotMap.liftTolerance);
		 //SmartDashboard.putData("Lift State:  ", Robot.lift);
		 //SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
	 }
	
	//   By default, we want the Lift Off to not drain the battery when its not being called.
	public void initDefaultCommand() {
    
    }
    
    public void liftUp() {
    	//  Move the Lift up.
       	liftMotor.set(RobotMap.liftMotorPowerUp);	
       	SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
    }
    
    public void lift(double yValue) {
    	//  Move the Lift.	
    	
    	//Send the distance and power to the Smart Dashboard.
    	SmartDashboard.putNumber("Lift Power:" , yValue);
    	SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
    	//Stop the lift if we are within a quarter of an inch.
    	if (yValue < .25 && yValue > -.25) {
       		liftMotor.set(0);
       	}
       	else if (yValue <-.25) {
       		liftMotor.set(RobotMap.liftMotorPowerUp);
       	}
       	else if (yValue >.25) {
       		liftMotor.set(RobotMap.liftMotorPowerDown);
       	}
    }
    
    public void liftUpTurbo() {
    	//  Move the Lift Up REALLY FAST.  (Turbo)
    	//  NOTE:  NEVER USED THIS IN COMPETITION
    	liftMotor.set(RobotMap.liftMotorPowerTurbo);	
    }
    
    public void liftDown() {
    	//  Move the Lift Down.
    	//if (getDistance() <= 15)  {
    		//liftMotor.set(RobotMap.liftThrottleDown);
    	//}else {
    		liftMotor.set(RobotMap.liftMotorPowerDown);
    		SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
    }
  
    
    public void liftOff() {
    	//  Power the Lift Off.
    	liftMotor.set(RobotMap.liftMotorPowerOff);	
    	SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
    }
    
    public void liftMove(double power) {
    	//  PID method used to move the lift up/down
    	SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
    	SmartDashboard.putNumber("Lift Power", power);
    	
    	//  This if/else-if/else is used to throttle the lift's speed to not jam it and damage it or
    	//   torque it on top of the robot and make it tipsy.
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
		SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
	}
	
    public void setEncoderDistancePerPulse() {
    	//Set the distance per pulse per Rotary Encoder.    Got this through calibration and testing.
    			//Check to see if we are using the Mule robot, and if so, use it's distance per pulse.  Otherwise, use CuBert's value
    			if (Robot.getWhichRobot() == RobotMap.MULE) {
    				liftEncoder.setDistancePerPulse(RobotMap.liftDistancePerPulse_mule);	
    				SmartDashboard.putString("Robot Selected", "MULE");
    			}
    			else {
    				liftEncoder.setDistancePerPulse(RobotMap.liftDistancePerPulse_cubert);
    				SmartDashboard.putString("Robot Selected", "Cube*rt");
       			}
    	
    }
}




