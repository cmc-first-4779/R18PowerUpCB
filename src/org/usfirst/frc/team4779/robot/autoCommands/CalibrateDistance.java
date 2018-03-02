package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CalibrateDistance extends CommandGroup {

    public CalibrateDistance(double distance) {
    	//Using this to calibrate distances.
    	//addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
    	double m_distance = distance;
		addSequential(new DriveStraightPIDWithThrottle(m_distance, RobotMap.AISLE_SPEED, RobotMap.FORWARD, true, RobotMap.NORTH));
		//addSequential(new DriveStraightPID(RobotMap.SCALE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    }
}
