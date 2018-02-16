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
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveJoystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	//Declare the Robot Subsystems.   
	public static Lift lift;
	public static DriveTrain driveTrain;
//	public static DriveTrainStraightPID driveTrainStraightPID;
//	public static DriveTrainTurnPID driveTrainTurnPID;
	public static VacCube vacCube; 
	public static Bling bling;
	//Declare the variables needed for the Field Management System for Red/Blue Tiles
	public static char mySwitchSide;
	public static char myScaleSide;
	public static char opponentSwitchSide;
	//Declare our SmartDashboardInit
	public static SmartDashboardInit smartDashboardInit;
	//Our standard practice is to leave the OI last.	
	public static OI m_oi;  

	//This is where we will start to offer different options for Auton based on our position in the 
	// starting field and what the FMS tells us.
	Command m_autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//Initiate the Robot Subsystems
		lift = new Lift();
		driveTrain = new DriveTrain();
//		driveTrainStraightPID = new DriveTrainStraightPID();
//		driveTrainTurnPID = new DriveTrainTurnPID();
		vacCube = new VacCube();
//		bling = new Bling();
		
		//Initiate the OI.   NOTE:  ALWAYS INITIATE THE OI LAST!
		m_oi = new OI();
		
		//Calibrate our Gyro
		Robot.driveTrain.calibrateGyro();
		//Robot.driveTrain.resetGyro();
		
		//Reset our Lift Encoder
		Robot.lift.resetLiftEncoder();
		
		//Init our SmartDashboard
		smartDashboardInit = new SmartDashboardInit();
		
		//Init our Camera..
		CameraServer.getInstance().startAutomaticCapture();

		SmartDashboard.putData("Auto mode", autoChooser);
		SmartDashboard.putData(vacCube);
		SmartDashboard.putData(Robot.driveTrain);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.driveTrain.setDefaultCommand(null);
		//Select the Auton Command Group from the SmartDashboard.
		//Get Game Data from FMS to tell where the Red & Blue Tiles are
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0) {
			if (gameData.charAt(0) == 'L'){
					mySwitchSide = 'L';
			}
			else {
				mySwitchSide='R';
			}
			
			/*mySwitchSide = gameData.charAt(0);
			myScaleSide = gameData.charAt(1);
			opponentSwitchSide = gameData.charAt(2);*/
		}
		else {
			System.out.println("No game data received");
		}
		if (mySwitchSide == 'R') {
			SmartDashboard.putString("mySwitch2", "Is R");
		}
		else {
			SmartDashboard.putString("mySwitch2", "Not R");
		}
		SmartDashboard.putString("mySwitch", new StringBuilder(mySwitchSide).toString());
		SmartDashboard.putString("gamedata", gameData);
		//  Send the default Auton Mode to the Java SmartDashboard.
		System.out.println("Before autoChooser");
		autoChooser.addDefault("Middle Switch", new MiddleSwitch());
		System.out.println("After autoChooser");
		autoChooser.addObject("Left Switch", new LeftSwitch());
		autoChooser.addObject("Right Switch", new RightSwitch());
		autoChooser.addObject("Middle Scale", new MiddleScale());
		autoChooser.addObject("Left Scale", new LeftScale());
		autoChooser.addObject("Right Scale", new RightScale());
		m_autonomousCommand = autoChooser.getSelected();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

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
		Robot.driveTrain.setDefaultCommand(new DriveJoystick());
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
}
