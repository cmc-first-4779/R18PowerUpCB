package org.usfirst.frc.team4779.robot.commands.vaccube;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  Eject a PowerCube from the VacCube using medium power.  
 *  
 *  NOT USED IN COMPETITION.
 */
public class VacCubeMediumEject extends Command {

    public VacCubeMediumEject() {
    	requires(Robot.vacCube);
    	setTimeout(1);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vacCube.vacCubeMediumEject();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.vacCube.vacCubeMediumEject();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.vacCube.vacCubeOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.vacCube.vacCubeOff();
    }
}
