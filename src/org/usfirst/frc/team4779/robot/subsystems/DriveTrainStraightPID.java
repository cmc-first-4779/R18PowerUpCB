package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DriveTrainStraightPID extends PIDSubsystem {
	public double drive_speed;
	public int direction;

    // Initialize your subsystem here
    public DriveTrainStraightPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("DriveStraight", .01, 0.00, 0.000);
		setAbsoluteTolerance(RobotMap.dTEncoderAbsoluteTolerance);
		setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);
		RobotMap.dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		RobotMap.dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		SmartDashboard.putNumber("Drive Angle", getPIDController().get());
		SmartDashboard.putNumber("Drive Power", drive_speed);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    		return RobotMap.gyro.getAngle();
    	}

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//RobotMap.driveTrainRobotDrive.drive(drive_speed, direction*output);	
    	RobotMap.myDrive.arcadeDrive(drive_speed, direction*output);
    	SmartDashboard.putNumber("Direction:  ", direction);
		SmartDashboard.putNumber("PID Output:  ", output);
    }
    
    public void calibrateGyro () {
    	RobotMap.gyro.calibrate();
    	System.out.println("Calibration of Gyro Complete");
    }
    
    public void resetGyro() {
    	RobotMap.gyro.reset();
    	System.out.println("Reset of Gyro Complete");
    }
    
    public double getGyroAngle() {
    	return RobotMap.gyro.getAngle();
    }
    
    public double getAvgEncoderPosition() {
		
		return (RobotMap.dTEncoderLeft.getDistance() + RobotMap.dTEncoderRight.getDistance()) / 2;
	}
    
    public void resetDTEncoders() {
    	RobotMap.dTEncoderLeft.reset();
    	RobotMap.dTEncoderRight.reset();
    }
}
