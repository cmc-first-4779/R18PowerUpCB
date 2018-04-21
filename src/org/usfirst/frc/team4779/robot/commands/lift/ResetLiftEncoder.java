package org.usfirst.frc.team4779.robot.commands.lift;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *   Because we did not have a potentiometer on the lift, we used a lift encoder.
 *   Occaisionally, the lift encoder values would drift during teleop in a match, and so the operator
 *   could drive the lift down using the joystick to the absolute bottom of its range, and then call this
 *   command using a button to reset that bottom-most value to ZERO.
 */
public class ResetLiftEncoder extends Command {

    public ResetLiftEncoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    	setTimeout(.2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.resetLiftEncoder();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
