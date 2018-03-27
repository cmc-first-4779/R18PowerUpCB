package org.usfirst.frc.team4779.robot.commands.lift;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLiftSetPointPID extends Command {

	private double setpoint;
	
	
    public SetLiftSetPointPID(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.setpoint = setpoint;
    	setTimeout(3.5);  //Set time out for five seconds.
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.enable();
    	Robot.lift.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.lift.onTarget() || isTimedOut() );
    	//return false; 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.disable();
    	Robot.lift.liftOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.disable();
    	Robot.lift.liftOff();	
    }
}
