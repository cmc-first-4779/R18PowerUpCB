package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightPID extends Command {

	double m_distance;
	double m_speed;
	double m_time;
	int m_direction;
	Timer timer = new Timer();
	
    public DriveStraightPID(double distance, double speed, double time, int direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_distance = distance;
		m_speed = speed;
		m_time = time;
		m_direction = direction;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetDTEncoders();
    	Robot.driveTrain.resetGyro();
    	Robot.driveTrain.setDirection(m_direction); 
    	Robot.driveTrain.setSpeed(m_speed);
     	Robot.driveTrain.setSetpoint(0);
     	Robot.driveTrain.enable();

    	
    	/*RobotMap.EncoderLeft.reset();
		RobotMap.EncoderRight.reset();
		t.reset();
		t.start();
		RobotMap.navX.reset();
		Robot.drivePID.direction = m_direction;
		Robot.drivePID.drive_speed = m_speed; 
		Robot.drivePID.enable();
		Robot.drivePID.setSetpoint(0);*/
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(Robot.driveTrain.getAvgEncoderPosition()) < m_distance){
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
