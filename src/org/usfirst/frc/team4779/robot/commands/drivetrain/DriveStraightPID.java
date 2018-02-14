package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *  THIS COMMAND AS A PART OF AN AUTON COMMAND GROUP TO DRIVE THE ROBOT AT A INPUTTED DISTANCE, SPEED, AND DIRECTION
 *  IT USES THE GYRO TO STAY ON COURSE AND THE ROTARY ENCODERS TO MEASURE HOW FAR IT TRAVELLED.
 */
public class DriveStraightPID extends Command {

	double m_distance;
	double m_speed;
	int m_direction;
	//NO TIMER USED!!   WE WILL MOST LIKELY DELETE THIS!
	Timer timer = new Timer();
	
    public DriveStraightPID(double distance, double speed, int direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//HERE ARE OUR THREE INPUTS.  DISTANCE, SPEED, and DIRECTION (1 = FORWARD, -1 = REVERSE)
    	m_distance = distance;
		m_speed = speed;
		m_direction = direction;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//RESET OUR ENCODERS
    	Robot.driveTrain.resetDTEncoders();
    	System.out.println("Reset DT Encoders.");
    	//RESET OUR GYRO
    	Robot.driveTrain.resetGyro();
    	//SET THE PID DIRECTION.  1 = FORWARD, -1 = BACKWARDS
    	Robot.driveTrain.setDirection(m_direction); 
    	//SET OUR PID SPEED.   (NOTE:  BY MULTIPLYING SPEED BY DIRECTION, OUR PID WILL KNOW WHICH DIRECTION TO GO
    	Robot.driveTrain.setSpeed(m_speed * m_direction);
    	//SET OUR GYRO TO ZERO!   WE WANT TO GO STRAIGHT!
     	Robot.driveTrain.setSetpoint(0);
     	//ENABLE THE PID!
     	Robot.driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//KEEP RUNING THE PID IF THE ABSOLUTE VALUE OF THE AVERAGE ENCODER POSITION IS LESS THAN THE REQUIRED DISTANCE.
    	if(Math.abs(Robot.driveTrain.getAvgEncoderPosition()) < Math.abs(m_distance)){
			return false;
		}
    	//ONCE THE AVERAGE ENCODER POSITION IS >= THAN OUR DESIRED DISTANCE, THE PID IS DONE!
		else {
			return true;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	//DISABLE THE PID!
    	Robot.driveTrain.disable();
    	//SHUT DOWN POWER TO THE MOTORS!
		Robot.driveTrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//DISABLE THE PID!
    	Robot.driveTrain.disable();
    	//SHUTDOWN POWER TO THE MOTORS!
		Robot.driveTrain.arcadeDrive(0, 0);
    }
}
