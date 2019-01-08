package org.usfirst.frc.team4779.robot.limelight;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LimelightSetLEDModeOn extends Command {

    public LimelightSetLEDModeOn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.limelight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.limelight.setLEDMode(RobotMap.LIMELIGHT_LEDMODE_ON);
    	SmartDashboard.putNumber("Limelight Valid Target: ", Robot.limelight.getTV());
    	SmartDashboard.putNumber("Limelight TX: ", Robot.limelight.getTX());
    	SmartDashboard.putNumber("Limelight TY: ", Robot.limelight.getTY());
    	SmartDashboard.putNumber("Limelight TA: ", Robot.limelight.getTA());
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
