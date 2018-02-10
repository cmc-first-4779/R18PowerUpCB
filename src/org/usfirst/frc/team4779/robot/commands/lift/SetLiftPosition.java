package org.usfirst.frc.team4779.robot.commands.lift;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *  Move the Lift UP.
 */
public class SetLiftPosition extends Command {
	private PIDController pid;

    public SetLiftPosition(double position) {
    	requires(Robot.lift);

    	pid = new PIDController(1, 0, 0, new PIDSource() {
    		PIDSourceType sourceType = PIDSourceType.kDisplacement;
    		
    		public double pidGet() {
    			return Robot.lift.getPosition();
    		}
    		
    		public void setPIDSourceType(PIDSourceType pidSource) {
    			sourceType = pidSource;
    		}
    		
    		public PIDSourceType getPIDSourceType() {
    			return sourceType;
    		}
    	}, pos -> Robot.lift.liftMove(pos));
    	
    	pid.setAbsoluteTolerance(.02);
    	pid.setSetpoint(position);
    }
    
    	
    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.reset();
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	Robot.lift.liftUp();
//    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
