package org.usfirst.frc.team4779.robot.commands.drivetrain;

import org.usfirst.frc.team4779.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnPID extends Command {
	double m_speed;
	double m_setpoint;

    public DriveTurnPID(double speed, double setpoint) {
    	m_speed = speed;
		m_setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*GyroPID.turn_speed = m_speed;
		RobotMap.navX.reset();
		Robot.gyroPID.enable();
		Robot.gyroPID.setSetpoint(m_setpoint);*/
    	Robot.driveTrainTurnPID.resetGyro();
    	Robot.driveTrainTurnPID.enable();
    	Robot.driveTrainTurnPID.setSetpoint(m_setpoint);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if(Math.abs(Robot.driveTrainTurnPID.returnPIDInput()) >= Math.abs(Robot.driveTrainTurnPID.getSetpoint())){
    				/*return true;
		}
		
		else{
			return false;
		}*/
    	return Robot.driveTrainTurnPID.onTarget();
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainTurnPID.disable();
    	Robot.driveTrain.arcadeDrive(0, 0);
    	}
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainTurnPID.disable();
    	Robot.driveTrain.arcadeDrive(0, 0);
    	}
}
