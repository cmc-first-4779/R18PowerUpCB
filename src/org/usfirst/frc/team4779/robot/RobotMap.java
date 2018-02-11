/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4779.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Map out the SPARK controllers.
	public static int frontLeftDrivePWMPort = 0;
	public static int frontRightDrivePWMPort = 1;
	public static int rearLeftDrivePWMPort = 2;
	public static int rearRightDrivePWMPort = 3;
	public static int liftMotorPWMPort = 4;
	public static int vacCubeLeftMotorPWMPort = 5;
	public static int vacCubeRightMotorPWMPort = 6;
	
	//Map out the Joystick #'s in the DriverStation USB Ports
	public static int driverStickUSBPort = 0;
	public static int operStickUSBPort = 1;
	
	//These are the buttons on the Joysticks as recognized by the Drivers Station. You can call in other subsystems.
	public static int aButton = 1;
	public static int bButton = 2;
	public static int xButton = 3;
	public static int yButton = 4;
	public static int leftBumperButton = 5;
	public static int rightBumperButton = 6;
	public static int xAxisStick = 0;
	public static int yAxisStick = 1;
	public static int leftTrigger = 2;
	public static int rightTrigger = 3;
		
		
	//Here are the initial SPARK Motor Power settings.   We will need to tune these as we test.
	//We keep them here so that we ALWAYS know where to check for them if we are troubleshooting.
	public static double liftMotorPowerUp = 1.0;
	public static double liftMotorPowerTurbo = 1.0;
	public static double liftMotorPowerDown = -1.0;
	public static double liftMotorPowerClimb = -0.5;
	public static double liftMotorPowerOff = 0;
	public static double vacCubeMotorPowerIntake = -0.5;
	public static double vacCubeMotorPowerEject = 0.5;
	public static double vacCubeMotorPowerHold = 0.2;
	public static double vacCubeMotorPowerOff = 0;
	
	
	public static double dTEncoderPulsePerRevolution = 20;
	public static double dTEncoderDistancePerRevolution = 14.2;//We need to find this.
	public static double dTDistancePerPulse = dTEncoderDistancePerRevolution / dTEncoderPulsePerRevolution;
	public static int dTEncoderLeftChannelA = 0;
	public static int dTEncoderLeftChannelB = 1;
	public static int dTEncoderRightChannelA = 2;
	public static int dTEncoderRightChannelB = 3;
	public static double dTEncoderAbsoluteTolerance = 2.5;
	public static double dTEncoderOutputMin = -1;
	public static double dTEncoderOutputMax = 1;
	public static int dTStraightPIDForward = 1;
	public static int dTStraightPIDReverse = -1;
	
	
	
	public static double dTTurnPIDTurnSpeed = 0.4;
	public static double dTTurnAbsoluteTolerance = 1.0;
	
	public static int liftEncoderChannelA = 4;
	public static int liftEncoderChannelB = 5;
	public static double liftEncoderPulsePerRevolution = 20;
	public static double liftEncoderDistancePerRevolution = 14.2;//We need to find this.
	public static double liftDistancePerPulse = liftEncoderDistancePerRevolution / liftEncoderPulsePerRevolution;
	
	
	//SpeedControlers and Drive Train
	public static Spark frontLeftDrive;// = new Spark(frontLeftDrive);
	public static Spark frontRightDrive;
	public static Spark rearLeftDrive;
	public static Spark rearRightDrive;
	//  Declare the two speed control groups.  (Left side and Right Side) 
	public static SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	public static SpeedControllerGroup myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);	
	//  Using the two speed controller groups, Declare our Differential Drive.
	public static DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);
	
	//Gyro
	public static ADXRS450_Gyro gyro;
	
	//Encoders
	public static Encoder dTEncoderLeft;
	public static Encoder dTEncoderRight;
	
	
	public static void init() {
		frontLeftDrive = new Spark(frontLeftDrivePWMPort);
		frontRightDrive = new Spark(RobotMap.frontRightDrivePWMPort);
		rearLeftDrive = new Spark(RobotMap.rearLeftDrivePWMPort);
		rearRightDrive = new Spark(RobotMap.rearRightDrivePWMPort);
		
		myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
		myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);
		
		myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);
		
		gyro = new ADXRS450_Gyro();
		
		dTEncoderLeft = new Encoder(dTEncoderLeftChannelA, dTEncoderLeftChannelB);
		dTEncoderRight = new Encoder(dTEncoderRightChannelA, dTEncoderRightChannelB);
	}
	
}

