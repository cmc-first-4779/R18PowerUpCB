package org.usfirst.frc.team4779.robot.limelight;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimelightSetPipelineVest extends Command {

    public LimelightSetPipelineVest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.limelight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.limelight.setPipeline(RobotMap.LIMELIGHT_PIPELINE_VEST);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
