package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  We use this Command in Teleop to control the DriveTrain Subsystem with the Joystick.
 */
public class DriveJoystick extends Command {

    public DriveJoystick() {
    	requires (Robot.driveTrain);
    }

    // Called just beore this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.arcadeDrive(Robot.m_oi.getDriverStick().getY(), Robot.m_oi.getDriverStick().getX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
