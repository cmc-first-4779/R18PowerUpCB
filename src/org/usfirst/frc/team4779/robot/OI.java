/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4779.robot;

import org.usfirst.frc.team4779.robot.autoCommands.CalibrateDistance;
import org.usfirst.frc.team4779.robot.autoCommands.LeftScale;
import org.usfirst.frc.team4779.robot.autoCommands.LeftSwitch;
import org.usfirst.frc.team4779.robot.commands.DeployPortal;
import org.usfirst.frc.team4779.robot.commands.DeployScale;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.LiftDown;
import org.usfirst.frc.team4779.robot.commands.lift.LiftUp;
import org.usfirst.frc.team4779.robot.commands.lift.ResetLiftEncoder;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeIntake;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeLowEject;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Declare the two joysticks and initate them on the two appropriate USB ports
	// recognized by the Drivers Station.
	Joystick driverStick = new Joystick(RobotMap.driverStickUSBPort);
	Joystick operStick = new Joystick(RobotMap.operStickUSBPort);

	public OI() {
		//// CREATING BUTTONS
		// One type of button is a joystick button which is any button on a
		//// joystick.
		// You create one by telling it which joystick it's on and which button
		// number it is.
		// Joystick stick = new Joystick(port);
		// Button button = new JoystickButton(stick, buttonNumber);

		// Declare and Initiate all of the Buttons on the OperStick
		JoystickButton operStickYButton = new JoystickButton(operStick, RobotMap.yButton);
		JoystickButton operStickXButton = new JoystickButton(operStick, RobotMap.xButton);
		JoystickButton operStickAButton = new JoystickButton(operStick, RobotMap.aButton);
		JoystickButton operStickBButton = new JoystickButton(operStick, RobotMap.bButton);
		JoystickButton operStickLeftBumper = new JoystickButton(operStick, RobotMap.leftBumperButton);
		JoystickButton operStickRightBumper = new JoystickButton(operStick, RobotMap.rightBumperButton);
		JoystickButton operStickBackButton = new JoystickButton(operStick, RobotMap.backButton);
		JoystickButton operStickStartButton = new JoystickButton(operStick, RobotMap.startButton);

		// Declare and Initiate all of the Buttons on the Driver Stick
		JoystickButton driverStickYButton = new JoystickButton(driverStick, RobotMap.yButton);
		JoystickButton driverStickXButton = new JoystickButton(driverStick, RobotMap.xButton);
		JoystickButton driverStickAButton = new JoystickButton(driverStick, RobotMap.aButton);
		JoystickButton driverStickBButton = new JoystickButton(driverStick, RobotMap.bButton);
		JoystickButton driverStickLeftBumper = new JoystickButton(driverStick, RobotMap.leftBumperButton);
		JoystickButton driverStickRightBumper = new JoystickButton(driverStick, RobotMap.rightBumperButton);
		JoystickButton driverStickBackButton = new JoystickButton(driverStick, RobotMap.backButton);
		JoystickButton driverStickStartButton = new JoystickButton(driverStick, RobotMap.startButton);

		// SETUP OUR JOYSTICK BUTTON MAPPINGS HERE!!!
		// Driver Stick
		operStickStartButton.whenPressed(new ResetLiftEncoder());
		// Operator Stick
		operStickLeftBumper.whileHeld(new VacCubeIntake());
		operStickRightBumper.whileHeld(new VacCubeEject());
		// operStickYButton.whenPressed(new SetLiftSetPointPID(RobotMap.scaleHeight));
		operStickBButton.whenPressed(new SetLiftSetPointPID(RobotMap.portalHeight));
		operStickXButton.whenPressed(new SetLiftSetPointPID(RobotMap.switchHeight));
		// operStickAButton.whenPressed(new SetLiftSetPointPID(RobotMap.pickUpHeight));
		operStickYButton.whileHeld(new VacCubeLowEject());
		driverStickRightBumper.whileHeld(new VacCubeIntake());
	}

	// This method is used later to return the the driverStick when called.
	public Joystick getDriverStick() {
		return driverStick;
	}

	// This method is used later to return the the operStick when called.
	public Joystick getOperStick() {
		return operStick;
	}

}
