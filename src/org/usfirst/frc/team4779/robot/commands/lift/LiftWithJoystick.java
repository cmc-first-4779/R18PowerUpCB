package org.usfirst.frc.team4779.robot.commands.lift;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *   USED TO MOVE THE LIFT WITH THE OPERATOR JOYSTICK.
 *   
 *   WE USED THIS EXTENSIVELY DURING TELEOP IN COMPETITIONS!!
 */
public class LiftWithJoystick extends Command {
	
	

    public LiftWithJoystick() {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Get the y-axis value from the operator joystick.
    	double yValue = Robot.m_oi.getOperStick().getY();
    	//  Move the Lift in the direction of the y-axis value.
    	Robot.lift.lift(yValue);
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
