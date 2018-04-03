/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4779.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4779.robot.autoCommands.*;
import org.usfirst.frc.team4779.robot.subsystems.Bling;
import org.usfirst.frc.team4779.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4779.robot.subsystems.Lift;
import org.usfirst.frc.team4779.robot.subsystems.VacCube;
import org.usfirst.frc.team4779.robot.commands.SmartDashboardInit;
import org.usfirst.frc.team4779.robot.commands.bling.BlingBlue;
import org.usfirst.frc.team4779.robot.commands.bling.BlingGreen;
import org.usfirst.frc.team4779.robot.commands.bling.BlingOrange;
import org.usfirst.frc.team4779.robot.commands.bling.BlingPurple;
import org.usfirst.frc.team4779.robot.commands.bling.BlingRed;
import org.usfirst.frc.team4779.robot.commands.bling.BlingWhite;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveJoystick;
import org.usfirst.frc.team4779.robot.commands.lift.LiftWithJoystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	// Declare the Robot Subsystems.
	public static Lift lift;
	public static DriveTrain driveTrain;
	public static VacCube vacCube;
	public static Bling bling;
	
	//public static double m_dtencoderDistancePerRevolution;	
	private static int whichRobot;

	// Declare the variables needed for the Field Management System for Red/Blue
	// Tiles
	public static char mySwitchSide;
	public static char myScaleSide;
	public static char opponentSwitchSide;

	// Declare our SmartDashboardInit
	//public static SmartDashboardInit smartDashboardInit;

	// Our standard practice is to leave the OI last.
	public static OI m_oi;

	//Command that will represent our Auton Command once we read it in
	Command m_autonomousCommand;
	
	//Command that will represent our BLING Command once we read it in
	Command m_blingCommand;
	
	//Create the two new choosers for the Robot Chooser and the Auton Chooser
	SendableChooser<Integer> robotChooser = new SendableChooser<>();
	SendableChooser<Integer> autoChooser = new SendableChooser<>();
	SendableChooser<Integer> blingChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Initiate the Robot Subsystems
		System.out.println("Initiating Robot Subsystems");
		lift = new Lift();
		driveTrain = new DriveTrain();
		vacCube = new VacCube();
		bling = new Bling();
		System.out.println("Subsystem initiation complete.");


		// Initiate the OI. NOTE: ALWAYS INITIATE THE OI LAST!
		System.out.println("Initiating the OI.");

    m_oi = new OI();
		System.out.println("OI initiating complete.");

		// Calibrate our Gyro
		System.out.println("Starting Gyro Calibration");
		Robot.driveTrain.calibrateGyro();
		System.out.println("Gyro Calibration Complete");
	

		// Reset our Lift Encoder
		System.out.println("Resetting Lift Encoder.");
		Robot.lift.resetLiftEncoder();
		System.out.println("Lift encoder reset complete.");
		//SmartDashboard.putNumber("Lift Encoder Distance:  ", Robot.lift.getDistance());

		//Turn on the Camera Server for the Dashboard
		System.out.println("Starting the camera server.");
		CameraServer.getInstance().startAutomaticCapture();
		System.out.println("Camera Server started.");

		//Add the Auton Chooser objects to the SmartDashboard.   Each is assigned an interger.

		autoChooser.addDefault("Middle Starting Position - Switch", 0);
		autoChooser.addObject("Left Starting Position -  Switch", 1);
		autoChooser.addObject("Right Starting Position - Switch", 2);
		autoChooser.addObject("Middle Starting Position - Scale", 3);
		autoChooser.addObject("Left Starting Position - Scale", 4);
		autoChooser.addObject("Right Starting Position - Scale", 5);
		autoChooser.addObject("Calibrate 10'", 6);
		autoChooser.addObject("Calibrate 15'", 7);
		autoChooser.addObject("Calibrate 20'", 8);
		autoChooser.addObject("Left Scale Non North", 9);
		autoChooser.addObject("Left Scale Angle", 10);
		autoChooser.addObject("Right Scale Angle", 11);

		
		//Add the Bling Chooser objects to the SmartDashboard.  Each is assigned an interger
		blingChooser.addDefault("Purple", RobotMap.PURPLE);
		blingChooser.addObject("Blue", RobotMap.BLUE);
		blingChooser.addObject("Red", RobotMap.RED);
		blingChooser.addObject("Orange", RobotMap.ORANGE);
		blingChooser.addObject("Green", RobotMap.GREEN);
    
		SmartDashboard.putData("Auto mode", autoChooser);
		SmartDashboard.putData(vacCube);
		SmartDashboard.putData(lift);
		SmartDashboard.putData(Robot.driveTrain);
		//SmartDashboard.putData(Robot.driveTrain.gyro);
		
		//Add the objects into the Robot Chooser for Cubert and the Mule.
		//  Hehe..   "Cubert and the Mule.."  Sounds like a 1970's TV show.
		robotChooser.addDefault("Cubert", RobotMap.CUBERT);
		robotChooser.addObject("Mule", RobotMap.MULE);
		
		SmartDashboard.putData("Choose Robot" , robotChooser); 
	}



	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//Get the robot setting from the dashboard
		setWhichRobot(robotChooser.getSelected());

		
		//Set the drive train encoders now that we know which robot
		Robot.driveTrain.setEncoderDistancePerPulse();
		
		//Turn safety off to get rid of error messages about not updating enough
		Robot.driveTrain.setMotorSafety(false);
		
		//Set the Lift encoder now that we know which robot
		Robot.lift.setEncoderDistancePerPulse();

		// Select the Auton Command Group from the SmartDashboard.
		// Get Game Data from FMS to tell where the Red & Blue Tiles are
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Got game data: " + gameData);
		if (gameData.length() > 0) {
			mySwitchSide = gameData.charAt(0);
			myScaleSide = gameData.charAt(1);
			opponentSwitchSide = gameData.charAt(2);
			System.out.println("My Swtich Side: " + mySwitchSide);
			System.out.println("My Scale Side: " + myScaleSide);
			System.out.println("Opponent Switch Side: " + opponentSwitchSide);

			SmartDashboard.putString("mySwitchSide value: ", new StringBuilder(mySwitchSide).toString());
			SmartDashboard.putString("myScaleSide value: ", new StringBuilder(myScaleSide).toString());
			SmartDashboard.putString("opponentSwitchSide value: ", new StringBuilder(opponentSwitchSide).toString());
		} else {
			System.out.println("No game data received");
		}

		SmartDashboard.putString("gamedata", gameData);
		// Create the proper auto  command based on the auton selection. 
		switch (autoChooser.getSelected().intValue()) {
		case 0:
			SmartDashboard.putString("Selected Command:", "Middle Switch");
			m_autonomousCommand = new MiddleSwitch();
			break;
		case 1:
			SmartDashboard.putString("Selected Command:", "Left Switch");
			m_autonomousCommand = new LeftSwitch();
			break;
		case 2:
			SmartDashboard.putString("Selected Command:", "Right Switch");
			m_autonomousCommand = new RightSwitch();
			break;
		case 3:
			SmartDashboard.putString("Selected Command:", "Middle Scale");
			m_autonomousCommand = new MiddleScale();
			break;
		case 4:
			SmartDashboard.putString("Selected Command:", "Left Scale");
			m_autonomousCommand = new LeftScale();
			break;
		case 5:
			SmartDashboard.putString("Selected Command:", "Right Scale");
			m_autonomousCommand = new RightScale();
			break;
		case 6: 
			SmartDashboard.putString("Selected Command:", "Calibrate 10'");
			m_autonomousCommand = new CalibrateDistance(120);
			break;
		case 7: 
			SmartDashboard.putString("Selected Command:", "Calibrate 15'");
			m_autonomousCommand = new CalibrateDistance(180);
			break;
		case 8: 
			SmartDashboard.putString("Selected Command:", "Calibrate 20'");
			m_autonomousCommand = new CalibrateDistance(240);
			break;
		case 9:
			SmartDashboard.putString("Selected Command:", "Left Scale non north");
			m_autonomousCommand = new LeftScaleNonNorth();
			break;
		case 10:
			SmartDashboard.putString("Selected Command:", "Left Scale Angle");
			m_autonomousCommand = new LeftScaleAngle();
			break;
		case 11:
			SmartDashboard.putString("Selected Command:", "Left Scale Angle");
			m_autonomousCommand = new RightScaleAngle();
			break;
		}
		
		// Create the proper BLING  command based on the BLING Chooser selection. 
		setBlingColor();

		Robot.lift.resetLiftEncoder();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//Get the robot setting from the dashboard
		setWhichRobot(robotChooser.getSelected());
		
		//Set the drive train encoders now that we know which robot
		Robot.driveTrain.setEncoderDistancePerPulse();
		
		//Set the Lift encoder now that we know which robot
		Robot.lift.setEncoderDistancePerPulse();
		
		//Set the default command to drive by the Joystick
		Robot.driveTrain.setDefaultCommand(new DriveJoystick());
		
		//This will null out some of the safety notices in the console..
		Robot.driveTrain.setMotorSafety(true);
		
		//Set the default command to lift with the Joystick on the OperStick
		Robot.lift.setDefaultCommand(new LiftWithJoystick());
		
		setBlingColor();

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.t
	 */
	@Override
	public void testPeriodic() {
	}

	/**
	 * Gets the value of which robot we are we using
	 * @return CUBERT or MULE depending on smart dashboard selection
	 */
	public static int getWhichRobot() {
		return whichRobot;
	}

	private void setWhichRobot(Integer selected) {
		SmartDashboard.putNumber("WhichRobot", selected);
		whichRobot = selected;
	}
	
	private void setBlingColor() {
		// Create the proper BLING  command based on the BLING Chooser selection. 
		switch (blingChooser.getSelected().intValue()) {
		case RobotMap.PURPLE:
			SmartDashboard.putString("Selected Bling:", "Purple");
			System.out.println("Bling color set to PURPLE.");
			m_blingCommand = new BlingPurple();
			break;
		case RobotMap.BLUE:
			SmartDashboard.putString("Selected Bling:", "Blue");
			System.out.println("Bling color set to BLUE.");
			m_blingCommand = new BlingBlue();
			break;
		case RobotMap.RED:
			SmartDashboard.putString("Selected Bling:", "Red");
			System.out.println("Bling color set to RED.");
			m_blingCommand = new BlingRed();
			break;
		case RobotMap.ORANGE:
			SmartDashboard.putString("Selected Bling:", "Orange");
			System.out.println("Bling color set to ORANGE.");
			m_blingCommand = new BlingOrange();
			break;
		case RobotMap.GREEN:
			SmartDashboard.putString("Selected Bling:", "Green");
			System.out.println("Bling color set to GREEN.");
			m_blingCommand = new BlingGreen();
			break;
		case RobotMap.WHITE:
			SmartDashboard.putString("Selected Bling:", "White");
			System.out.println("Bling color set to WHITE.");
			m_blingCommand = new BlingWhite();
			break;
		}
	}
}
	
	