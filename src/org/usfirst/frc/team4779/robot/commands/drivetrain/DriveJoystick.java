package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  We use this Command in Teleop to control the DriveTrain Subsystem with the Joystick.
 */
public class DriveJoystick extends Command {

    public DriveJoystick() {
    	requires (Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Call arcadeDrive with the driverstick to move the robot.
    	Robot.driveTrain.arcadeDrive(Robot.m_oi.getDriverStick().getY(), Robot.m_oi.getDriverStick().getX());
    	//Set the encoder values to the Dashboard.
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
