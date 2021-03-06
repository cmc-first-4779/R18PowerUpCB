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
	//Map out the SPARK controllers and PWM Ports.
	public static int frontLeftDrivePWMPort = 0;
	public static int frontRightDrivePWMPort = 1;
	public static int rearLeftDrivePWMPort = 2;
	public static int rearRightDrivePWMPort = 3;
	public static int liftMotorPWMPort = 4;
	public static int vacCubeLeftMotorPWMPort = 5;
	public static int vacCubeRightMotorPWMPort = 6;
	public static int climberPWMPort = 7;

	
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
	public static int backButton = 7;
	public static int startButton = 8;
	public static int xAxisStick = 0;
	public static int yAxisStick = 1;
	public static int leftTrigger = 2;
	public static int rightTrigger = 3;
		
	//Integers for the SmartDashboard Robot Chooser
	public static final int CUBERT = 0;
	public static final int MULE = 1;
	

	//Integers for the SmartDashboard Bling Chooser
	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int PURPLE = 2;
	public static final int ORANGE = 3;
	public static final int GREEN = 4;
	public static final int WHITE = 5;
	public static final int BLING_OFF = 6;
	
	//CAN Port that the Bling Subsystem is conneccted to
	public static final int BLING_CANLIGHT_PORT = 3;
	
	//Settings for using Proximity Sensor to try and get a cube.
	public static final double SWITCH_BACKUP_DISTANCE = 36;
	public static final double CUBE_PILE_APPROACH_DISTANCE = 54;
	public static final double GOT_CUBE_VOLTAGE = .005;

	
		
	//Here are the initial SPARK Motor Power settings.   We will need to tune these as we test.
	//We keep them here so that we ALWAYS know where to check for them if we are troubleshooting.
	public static double liftMotorPowerUp = 1.0;
	public static double liftMotorPowerTurbo = 1.0;
	public static double liftMotorPowerDown = -1.0;
	public static double liftMotorPowerClimb = -0.5;
	public static double liftMotorPowerOff = 0;
	public static double vacCubeMotorPowerIntake = -.95;

	public static double vacCubeMotorPowerEject = 0.8;
	public static double vacCubeMotorPowerMediumEject = 0.7;

	public static double vacCubeMotorPowerLowEject = 0.45;
	public static double vacCubeMotorPowerHold = 0.2;
	public static double vacCubeMotorPowerOff = 0;
	
	//DriveTrain PID and Encoder Settings.   Also DIO Mappings.
	public static double dTEncoderPulsePerRevolution = 20;
	//Cubert ration below
	public static double dTEncoderDistancePerRevolution_cubert = 2.32;//We need to find this.
	//Mule ratio below
	public static double dTEncoderDistancePerRevolution_mule = 2.76;//We need to find this
	public static double dTDistancePerPulse_mule = dTEncoderDistancePerRevolution_mule / dTEncoderPulsePerRevolution;
	public static double dTDistancePerPulse_cubert = dTEncoderDistancePerRevolution_cubert / dTEncoderPulsePerRevolution;
	//DT Encoder DIO Mappings
	public static int dTEncoderLeftChannelA = 0;
	public static int dTEncoderLeftChannelB = 1;
	public static int dTEncoderRightChannelA = 2;
	public static int dTEncoderRightChannelB = 3;
	//DT Encoder Absolute Tolerance
	public static double dTEncoderAbsoluteTolerance = 1.0;
	//DT PID Values
	public static double dTPValue = .1;
	public static double dtIValue = .0;
	public static double dtDValue = .0;
	


	//  Rotary Encoder Turn constants
	public static double dTEncoderOutputMin = -0.95;
	public static double dTEncoderOutputMax = 0.95;
	public static int FORWARD = 1;
	public static int REVERSE = -1;
	public static int LEFT = -90;
	public static int RIGHT = 90;
	public static int NORTH = 0;
	public static int EAST = 90;
	public static int SOUTH = 180;
	public static int WEST = -90;
	public static int LEFT_SIDE_CUBE_PILE_APPROACH_ANGLE = 45;
	public static int RIGHT_SIDE_CUBE_PILE_APPROACH_ANGLE = -45;
	
	
	//Lift Encoder Settings and DIO Mappings.
	public static double liftPValue = .8;
	public static double liftIValue = .2;
	public static double liftDValue = .0;
	public static int liftEncoderChannelA = 4;
	public static int liftEncoderChannelB = 5;
	public static double liftEncoderPulsePerRevolution = 20;
//	public static double liftEncoderDistancePerRevolution = 0.2368;  //We need to find this.
	public static double liftEncoderDistancePerRevolution_cubert = -.0045;  
	public static double liftEncoderDistancePerRevolution_mule = -.0046;  //We need to find this.
	public static double liftDistancePerPulse_cubert = liftEncoderDistancePerRevolution_cubert / liftEncoderPulsePerRevolution;
	public static double liftDistancePerPulse_mule = liftEncoderDistancePerRevolution_mule / liftEncoderPulsePerRevolution;
	public static double switchHeight = 30; 
	public static double scaleHeight = 77;
	public static double pickUpHeight = 0;
	public static double portalHeight = 3;
	public static double midScaleHeight = 65;
	public static double liftThrottleHeight = 76;
	public static double liftDTThrottleHeight = 40;
	public static double dTLiftThrottleDown = 0.75;  //HERE
	public static double liftThrottleDown = 0.0;  //Set to zero to stop slippage
	public static double dtTurnThrottle = 1;
	public static double dtTurnLiftedThrottle = .8;
	public static double liftTolerance = .5;
	public static double dTEncoderOutputMinTurn = -.75;
	public static double dTEncoderOutputMaxTurn = .75;
	
	//Climber Mappings
	public static double CLIMBER_MOTOR_SPEED = 0.90;
	public static double CLIMBER_MOTOR_OFF = 0.00;

	//Field Measurements for Auton  
	//  ALL OF THESE DISTANCES ARE IN INCHES!!!
	//
	//Distance from Left/Right Stations to the front of the Scale
	public static double FRONT_SCALE_DISTANCE = 309;
	//Distance we need to go to approach the front of the scale
	public static double FRONT_SCALE_APPROACH_DISTANCE_ANGLE = 8;
	public static double FRONT_SCALE_APPROACH_DISTANCE = 3;
	//Distance from the Left/Right Stations to the aisle between the Scale and the Switch
	public static double AISLE_DISTANCE = 218;
	//Speed as we go to the front 
	public static double FRONT_SCALE_FULL_SPEED =  1.0;
	//Speed as we throttle down.
	public static double THROTTLE_SPEED = 0.6;
	//public static double SCALE_THROTTLE_DOWN_DISTANCE = 100;
	public static double AISLE_LENGTH_TO_SCALE = 192;  //was 192, changed to 210.
	//public static double AISLE_APPROACH_THROTTLE_DISTANCE = 100;
	public static double AISLE_LENGTH_TO_SWITCH = 173;
	//public static double AISLE_THROTTLE_DOWN_DISTANCE = 40;
	//Speed we go down the aisle
	public static double AISLE_SPEED = 0.8;
	//Distance we approach the scale from the aisle
	public static double AISLE_SCALE_APPROACH_DISTANCE = 37;
	//Distance from the Left or Right starting stations to the "front" of the switch.
	public static double FRONT_SWITCH_DISTANCE = 145;
	//Speed we are traveling to the front of the switch.
	public static double FRONT_SWITCH_SPEED = 0.75;
	//Distance we are traveling after turning to touch the front of the switch.
	public static double FRONT_SWITCH_APPROACH_DISTANCE = 13;
	//Distance we are traveling after traveling down the aisle to touch the switch.
	public static double AISLE_SWITCH_APPROACH_DISTANCE = 14;
	public static double SIDE_SWITCH_DISTANCE = 98;
	public static double SWITCH_AISLE_APPROACH_DISTANCE = 73;
	public static double SWITCH_AISLE_DISTANCE = 24;
	public static double LIFT_SETPOINT_HIGH_SPEED = 40;
	public static double LIFT_SETPOINT_LOW_SPEED = 70;
	public static double MIDDLE_SWITCH_DISTANCE = 95;
	public static double SCALE_ANGLE_DISTANCE = 255;
	public static double FRONT_SCALE_ANGLE_APPROACH_DISTANCE = 42;
}

