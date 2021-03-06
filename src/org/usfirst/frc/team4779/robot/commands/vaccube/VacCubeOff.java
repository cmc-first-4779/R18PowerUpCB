package org.usfirst.frc.team4779.robot.commands.vaccube;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  Turn the VacCube Off when not being used.
 */
public class VacCubeOff extends Command {

    public VacCubeOff() {
    	requires(Robot.vacCube);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vacCube.vacCubeOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.vacCube.vacCubeOff();
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
