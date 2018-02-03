/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4779.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static int frontLeftDrive = 0;
	public static int frontRightDrive = 1;
	public static int rearLeftDrive = 2;
	public static int rearRightDrive = 3;
	public static int liftMotor = 4;
	public static int vaccubeLeftMotor = 5;
	public static int vaccubeRightMotor = 6;
	
	//Define the Joystick #'s in the DriverStation USB Ports
		public static int driverStickUSBPort = 0;
		public static int operStickUSBPort = 1;
		
		//These are the buttons on the Joysticks. You can call in other subsystems.
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
		
		
		
		public static double liftMotorPowerUp = 0.5;
		public static double liftMotorPowerTurbo = 0.75;
		public static double liftMotorPowerDown = -0.5;
		public static double liftMotorPowerClimb = -0.5;
		public static double liftMotorPowerOff = 0;
		public static double vaccubeMotorPowerIntake = 1.0;
		public static double vaccubeMotorPowerEject = -1.0;
		public static double vaccubeMotorPowerHold = 0.2;
		public static double vaccubeMotorPowerOff = 0;
	
}

