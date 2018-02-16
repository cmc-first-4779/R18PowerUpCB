package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  This Command is used in Auton Command Groups to turn the robot by a certain number of degrees.
 *  It has NO forward or reverse motion.
 */
public class DriveTurnPID extends Command {
	double m_speed;
	double m_setpoint;

    public DriveTurnPID(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		m_setpoint = setpoint;  //Our Setpoint is the Desired angle to turn.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Reset our Gyro to ZERO
   	   	Robot.driveTrain.resetGyro();
   	   	//Set our Speed to ZERO.  NO FORWARD MOTION.
    	Robot.driveTrain.setSpeed(0);  
    	//ENABLE THE PID AND LET IT DO ITS THING!
    	Robot.driveTrain.enable();
    	//TELL THE PID TO GO TO THE DESIRED ANGLE!!
    	Robot.driveTrain.setSetpoint(m_setpoint);   
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//  We are finished if the PID is ON TARGET!!
    	return Robot.driveTrain.onTarget();
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	//DISABLE THE PID ON END!!
    	Robot.driveTrain.disable();
    	//SHUT DOWN THE DRIVE FOR NOW!
    	Robot.driveTrain.arcadeDrive(0, 0);
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//DISABLE THE PID ON INTERRUPT!!
    	Robot.driveTrain.disable();
    	//SHUT DOWN THE DRIVE FOR NOW!
    	Robot.driveTrain.arcadeDrive(0, 0);
    	}
}
