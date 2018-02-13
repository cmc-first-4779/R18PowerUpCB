package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightPID extends Command {

	double m_distance;
	double m_speed;
	int m_direction;
	Timer timer = new Timer();
	
    public DriveStraightPID(double distance, double speed, int direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_distance = distance;
		m_speed = speed;
		m_direction = direction;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetDTEncoders();
    	System.out.println("Reset DT Encoders.");
    	Robot.driveTrain.resetGyro();
    	Robot.driveTrain.setDirection(m_direction); 
    	Robot.driveTrain.setSpeed(m_speed);
     	Robot.driveTrain.setSetpoint(0);
     	Robot.driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(Robot.driveTrain.getAvgEncoderPosition()) < Math.abs(m_distance)){
			return false;
		}
		else {
			return true;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
    }
}
