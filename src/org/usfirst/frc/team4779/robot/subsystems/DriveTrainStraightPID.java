package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
/**
 *
 */
public class DriveTrainStraightPID extends PIDSubsystem {
	public double drive_speed;
	public int direction;

	Spark frontLeftDrive = new Spark(RobotMap.frontLeftDrive);
	Spark frontRightDrive = new Spark(RobotMap.frontRightDrive);
	Spark rearLeftDrive = new Spark(RobotMap.rearLeftDrive);
	Spark rearRightDrive = new Spark(RobotMap.rearRightDrive);
	SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	SpeedControllerGroup myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);
	
	DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);

	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	private Encoder dTEncoderLeft = new Encoder (RobotMap.dTEncoderLeftChannelA, RobotMap.dTEncoderLeftChannelB);
	private Encoder dTEncoderRight = new Encoder(RobotMap.dTEncoderRightChannelA, RobotMap.dTEncoderRightChannelB);
	
	
    // Initialize your subsystem here
    public DriveTrainStraightPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("DriveStraight", .01, 0.00, 0.000);
		setAbsoluteTolerance(RobotMap.dTEncoderAbsoluteTolerance);
		setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);
		dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse);
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
    		return gyro.getAngle();
    	}

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//RobotMap.driveTrainRobotDrive.drive(drive_speed, direction*output);	
    	myDrive.arcadeDrive(drive_speed, direction*output);
    	SmartDashboard.putNumber("Direction:  ", direction);
		SmartDashboard.putNumber("PID Output:  ", output);
    }
    
    public void calibrateGyro () {
    	gyro.calibrate();
    	System.out.println("Calibration of Gyro Complete");
    }
    
    public void resetGyro() {
    	gyro.reset();
    	System.out.println("Reset of Gyro Complete");
    }
    
    public double getGyroAngle() {
    	return gyro.getAngle();
    }
    
    public double getAvgEncoderPosition() {
		
		return (dTEncoderLeft.getDistance() + dTEncoderRight.getDistance()) / 2;
	}
    
    public void resetDTEncoders() {
    	dTEncoderLeft.reset();
    	dTEncoderRight.reset();
    }
}
