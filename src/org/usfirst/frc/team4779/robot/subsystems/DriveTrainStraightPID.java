package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *   DriveTrainStraight is a PID Subsystem used During AUTON to use the gyro to drive the robot 
 *   in a certain direction at a certain speed.
 */
public class DriveTrainStraightPID extends PIDSubsystem {
	public double driveSpeed;
	public int direction;  //  Forward or Reverse.   (Forward = 1, Reverse = -1)

	//ALL OF OUR SPARK CONTROLLERS, GYRO, AND ENCODER ARE DECLARED AND INITIALIZED IN RobotMap.
	//  WE HAD TO DO THIS SO THAT THREE SUBSYSTEMS COULD SHARE THEM.
	
    // Initialize your subsystem here
    public DriveTrainStraightPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("DriveStraight", .01, 0.00, 0.000);  //  NEED TO TUNE THESE.
    	
    	//Set the tolerance of our "yaw" for driving straight.  IE. How far are of an angle are we willing 
    	//  To be off.
		setAbsoluteTolerance(RobotMap.dTEncoderAbsoluteTolerance);
		//Only allow the motors to get a range of our Min and Mix in the RobotMap.
		setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);
		// Set the distance per pulse of each rotary encoder. 
		RobotMap.dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		RobotMap.dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		// Send the current drive angle as well as the drive speed to the SmartDashboard.
		SmartDashboard.putNumber("Drive Angle:  ", getPIDController().get());
		SmartDashboard.putNumber("Drive Power:  ", driveSpeed);
    }

    public void initDefaultCommand() {
    	//No default command due to PID
    }

    protected double returnPIDInput() {
        // Our PID input in this subsystem in our Gyro angle.
    	// Get the Gyro angle.
    		return RobotMap.gyro.getAngle();
    	}

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//RobotMap.driveTrainRobotDrive.drive(drive_speed, direction*output);	
    	
    	//  Send our output to arcade drive and make a course correction by multipling the direction by the
    	//   PID Output.   
    	//  REMEMBER for "direction", Forward = 1, Reverse = -1
    	RobotMap.myDrive.arcadeDrive(driveSpeed, direction*output);
    	SmartDashboard.putNumber("Direction:  ", direction);
		SmartDashboard.putNumber("PID Output:  ", output);
    }
   
    public double getAvgEncoderPosition() {
    	  //  Average the rotary encoder distances on both the left and right sides of the drivetrain
		return (RobotMap.dTEncoderLeft.getDistance() + RobotMap.dTEncoderRight.getDistance()) / 2;
	}
    
    public void resetDTEncoders() {
        // Reset both the left and right encoders.
    	RobotMap.dTEncoderLeft.reset();
    	RobotMap.dTEncoderRight.reset();
    }
}
