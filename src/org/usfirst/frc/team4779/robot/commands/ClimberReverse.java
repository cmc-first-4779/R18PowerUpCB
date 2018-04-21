package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  This command turns the Climber in reverse to climb down.   
 *   
 *   NOTE:  We decided not to use the climber during competition.
 */
public class ClimberReverse extends Command {

    public ClimberReverse() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.climberReverse();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.climberReverse();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.climberOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.climberOff();
    }
}
