package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeployScale;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveAnglePID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	super("Right Scale");
	if (Robot.myScaleSide == 'L') {
		
		// addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_DISTANCE, RobotMap.FRONT_SCALE_FULL_SPEED,
				RobotMap.FORWARD, true, RobotMap.NORTH));
		addSequential(new TimerCommand(0.25));
		// addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
		addSequential(new DriveTurnPID(RobotMap.WEST, false));
		addSequential(new TimerCommand(0.25));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_LENGTH_TO_SCALE, RobotMap.AISLE_SPEED,
				RobotMap.FORWARD, false, RobotMap.WEST));
		addSequential(new TimerCommand(0.25));
		addSequential(new DriveTurnPID(RobotMap.NORTH, false));
		addSequential(new TimerCommand(0.25));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_SCALE_APPROACH_DISTANCE,
				RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH));
		// addSequential(new DeployScale());
	}
	
	else  {
		// addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.FRONT_SCALE_DISTANCE,
				RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD, true, RobotMap.NORTH));
		addSequential(new TimerCommand(.25));
		addSequential(new DriveTurnPID(RobotMap.WEST, false));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.FRONT_SCALE_APPROACH_DISTANCE,
				RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
		// addSequential(new DeployScale());
	}


    }
}
