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
//    	Robot.driveTrainStraightPID.resetDTEncoders();
    	timer.reset();
    	timer.start();
//    	Robot.driveTrainStraightPID.resetGyro();
//    	Robot.driveTrainStraightPID.direction = m_direction; 
//    	Robot.driveTrainStraightPID.drive_speed = m_speed;
//    	Robot.driveTrainStraightPID.enable();
//    	Robot.driveTrainStraightPID.setSetpoint(0);
    	
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
//    	if(Math.abs(Robot.driveTrainStraightPID.getAvgEncoderPosition()) < m_distance){
//			return false;
//		}
//		else {
//			return true;
//		}
//    }
 //   	return Robot.driveTrainStraightPID.onTarget();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
 //   	Robot.driveTrainStraightPID.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
 //   	Robot.driveTrainStraightPID.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
    }
}
