package org.usfirst.frc.team4779.robot.commands.ramp;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  Turn the LIFT Off.
 */
public class RampOff extends Command {

    public RampOff() {
    	requires(Robot.ramp);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ramp.rampOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ramp.rampOff();
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
