package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSeekLL extends Command {

	boolean targetFound = false;
	
    public DriveSeekLL() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.limelight.setPipeline(RobotMap.LIMELIGHT_PIPELINE_CARGO);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Drive in a circle seeking the target on the Limelight...
    	//if there is no target..
    	if (Robot.limelight.getTV() == RobotMap.LIMELIGHT_NO_TARGET)  {
    		Robot.driveTrain.arcadeDrive(0, RobotMap.LIMELIGHT_SEEK_TURN_POWER);
//    		SmartDashboard.putNumber("Limelight TV:", Robot.limelight.getTV());
//        	SmartDashboard.putNumber("Limelight TX:", Robot.limelight.getTX());
//        	SmartDashboard.putNumber("Limelight TY:", Robot.limelight.getTY());
//        	SmartDashboard.putNumber("Limelight TA:", Robot.limelight.getTA());
//        	SmartDashboard.putNumber("Limelight AX:", Robot.limelight.getAX());
//        	SmartDashboard.putNumber("Limelight AY:", Robot.limelight.getAY());
//        	SmartDashboard.putNumber("Limelight Distance:", Robot.limelight.getDistance());
    		targetFound = false;
    		
    	}
    	
    	else {
    		targetFound = true;
    		//new DriveTurnPID(Robot.limelight.getAX());  //Turn the Robot towards the target...
    	}
    		
    }
    	
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return targetFound;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
