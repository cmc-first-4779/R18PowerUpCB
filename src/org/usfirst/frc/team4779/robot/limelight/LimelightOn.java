package org.usfirst.frc.team4779.robot.limelight;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimelightOn extends Command {

    public LimelightOn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.limelight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.limelight.limelightOn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.limelight.limelightOn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
