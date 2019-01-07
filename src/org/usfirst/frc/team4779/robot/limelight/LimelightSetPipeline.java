package org.usfirst.frc.team4779.robot.limelight;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimelightSetPipeline extends Command {

	int m_pipelineNumber;
	
    public LimelightSetPipeline(int pipelineNumber) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_pipelineNumber = pipelineNumber;
    	requires(Robot.limelight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.limelight.limelightSetPipeline(m_pipelineNumber);
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
