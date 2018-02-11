package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveJoystick;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 *   The DriveTrain Subsystem is where we drive power to the Spark Controllers to move the robot.
 */
public class DriveTrain extends PIDSubsystem {
	
	//  Declare the four Spark controllers for each of the motors on the chassis.
	Spark frontLeftDrive = new Spark(RobotMap.frontLeftDrive);
	Spark frontRightDrive = new Spark(RobotMap.frontRightDrive);
	Spark rearLeftDrive = new Spark(RobotMap.rearLeftDrive);
	Spark rearRightDrive = new Spark(RobotMap.rearRightDrive);
	
	//AnalogGyro gyro = new AnalogGyro(1);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public int direction; //forward 1 or backward -1
	double Kp = 0.03;
	double speed;

	//  Declare the two speed control groups.  (Left side and Right Side) 
	SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	SpeedControllerGroup myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);
	
	//  Using the two speed controller groups, Declare our Differential Drive.
	DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);

	private Encoder dTEncoderLeft = new Encoder (RobotMap.dTEncoderLeftChannelA, RobotMap.dTEncoderLeftChannelB);
	private Encoder dTEncoderRight = new Encoder(RobotMap.dTEncoderRightChannelA, RobotMap.dTEncoderRightChannelB);
	

	public DriveTrain() {
		super("DriveTrain", -1, 0, 0);
		setAbsoluteTolerance(RobotMap.dTEncoderAbsoluteTolerance);
		setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);
		dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse);
		SmartDashboard.putNumber("Drive Angle: ", getPIDController().get());
		SmartDashboard.putNumber("Drive Power: ", speed);
		SmartDashboard.putData(gyro);
		SmartDashboard.putNumber("Encoder Avg Distance: ", getAvgEncoderPosition());
	}
	
    public void initDefaultCommand() {
    	//  Our Default Command is to Drive using the Joystick.
        setDefaultCommand(new DriveJoystick());
        //gyro.reset();
    }
    
    public void arcadeDrive(double yAxis, double xAxis) {
    	//  This is our where we define arcadeDrive within the Subsystem
    	//  NOTE:  the xAxis off of the Joystick below is INVERTED.
    	myDrive.arcadeDrive(-yAxis, xAxis);
    }
    
    public void arcadeDriveWithGryo() {
		SmartDashboard.putNumber("Gryo Angle", gyro.getAngle());
    	double angle = gyro.getAngle();
    	System.out.println("Angle: " + angle);
    	myDrive.arcadeDrive(-.4, Kp*-angle );
    }
    
    public void resetGyro() {
    	gyro.reset();
    }
    
    public void calibrateGyro() {
    	gyro.calibrate();
    }
    
    public void stop() {
    	//  If needed, we can stop the driveTrain by sending 0's to arcadeDrive.
    	myDrive.arcadeDrive(0,0);
    }

        public void setSpeed (double speed) {
        	this.speed = speed;
        }
        
        public void setDirection(int dir) {
        	this.direction = dir;
        }
        
        public void resetDTEncoders() {
        	dTEncoderLeft.reset();
        	dTEncoderRight.reset();
        }
        
        public double getAvgEncoderPosition() {
    		
    		return (dTEncoderLeft.getDistance() + dTEncoderRight.getDistance()) / 2;
    	}
        
		@Override
		protected double returnPIDInput() {
			// TODO Auto-generated method stub
			return gyro.getAngle();
		}

		@Override
		protected void usePIDOutput(double output) {
			// TODO Auto-generated method stub
			myDrive.arcadeDrive(speed, direction*output);
		}
}

