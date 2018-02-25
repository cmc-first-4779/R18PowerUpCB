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

	
	
	public Lift() {
		 super("Lift", RobotMap.liftPValue, RobotMap.liftIValue, RobotMap.liftDValue);
		 liftEncoder.setDistancePerPulse(RobotMap.liftDistancePerPulse);
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
    	//  Move the Lift up.	
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
   // }
    
    
    public void liftOff() {
    	//  Power the Lift Off.
    	liftMotor.set(RobotMap.liftMotorPowerOff);	
    	SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
    }
    
    public void liftMove(double power) {
    	SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
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
		SmartDashboard.putNumber("Lift Encoder Position: ", Robot.lift.getDistance());
	}
}




