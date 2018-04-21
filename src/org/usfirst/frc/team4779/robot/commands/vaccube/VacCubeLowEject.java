package org.usfirst.frc.team4779.robot.commands.vaccube;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  Eject a PowerCube from the VacCube low power.   Can be used both in teleop and auton.    
 *  
 *  Low power ejects are used if the scale is full or if we want to position the cube such that we can 
 *  get leverage on the scale.
 */
public class VacCubeLowEject extends Command {

    public VacCubeLowEject() {
    	requires(Robot.vacCube);
    	setTimeout(1);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vacCube.vacCubeLowEject();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.vacCube.vacCubeLowEject();
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
