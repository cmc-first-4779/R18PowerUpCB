package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CalibrateDistance extends CommandGroup {

    public CalibrateDistance() {
    	//addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
		addSequential(new DriveStraightPID((277 - RobotMap.SCALE_THROTTLE_DOWN_DISTANCE), RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD));
		addSequential(new DriveStraightPID(RobotMap.SCALE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    }
}
