package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSeek extends Command {

    public DriveSeek() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Drive in a circle seeking the target on the Limelight...
    	//if there is no target..
    	if (Robot.limelight.getTV() == RobotMap.LIMELIGHT_NO_TARGET)  {
    		Robot.driveTrain.arcadeDrive(0, RobotMap.LIMELIGHT_SEEK_VALUE);
    	}
    	
    	else {
    		Robot.driveTrain.arcadeDrive(0, Robot.limelight.getTX());
    	}
    	
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
