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
	
	//Map out the SPARK controllers and PWM Ports.
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
	public static double vacCubeMotorPowerIntake = -.6;
	public static double vacCubeMotorPowerEject = 1;
	public static double vacCubeMotorPowerHold = 0.2;
	public static double vacCubeMotorPowerOff = 0;
	
	//DriveTrain PID and Encoder Settings.   Also DIO Mappings.
	public static double dTEncoderPulsePerRevolution = 20;
	public static double dTEncoderDistancePerRevolution = 2.255;//We need to find this.
  //Mule ratio below
	//public static double dTEncoderDistancePerRevolution = 4.22;//We need to find this.
	public static double dTDistancePerPulse = dTEncoderDistancePerRevolution / dTEncoderPulsePerRevolution;
	public static int dTEncoderLeftChannelA = 0;
	public static int dTEncoderLeftChannelB = 1;
	public static int dTEncoderRightChannelA = 2;
	public static int dTEncoderRightChannelB = 3;
	public static double dTEncoderAbsoluteTolerance = 1.0;
	public static double dTPValue = .2;
	public static double dtIValue = .0;
	public static double dtDValue = .0;
	



	public static double dTEncoderOutputMin = -0.85;
	public static double dTEncoderOutputMax = 0.85;
	public static int FORWARD = 1;
	public static int REVERSE = -1;


	public static double dTTurnPIDTurnSpeed = 0.4;
	public static double dTTurnAbsoluteTolerance = 1.0;
	
	//Lift Encoder Settings and DIO Mappings.
	public static double liftPValue = .5;
	public static double liftIValue = .2;
	public static double liftDValue = .0;
	public static int liftEncoderChannelA = 4;
	public static int liftEncoderChannelB = 5;
	public static double liftEncoderPulsePerRevolution = 20;
	public static double liftEncoderDistancePerRevolution = 0.2368;  //We need to find this.
	public static double liftDistancePerPulse = liftEncoderDistancePerRevolution / liftEncoderPulsePerRevolution;
	public static double switchHeight = 26; 
	public static double scaleHeight = 76.0;
	public static double pickUpHeight = 0;
	public static double portalHeight = 3;
	public static double liftThrottleHeight = 76;
	public static double liftDTThrottleHeight = 28;
	public static double dTLiftThrottleDown = 0.5;
	public static double liftThrottleDown = 0.5;
	public static double dtTurnThrottle = 0.7;
	public static double dtTurnLiftedThrottle = .6;
	public static double liftTolerance = .5;
	public static double dTEncoderOutputMinTurn = -.65;
	public static double dTEncoderOutputMaxTurn = .65;

}

