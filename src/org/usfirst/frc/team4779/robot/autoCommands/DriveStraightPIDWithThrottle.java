package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *   The DriveStraightPIDWith Throttle Command is used during Auton to drive the robot a certain distance,
 *   speed, direction using the gyro and rotary encoders.    
 *   
 *   With this code, if the robot gets off target mid-drive, it will attempt to correct itself.
 *   
 *   There is also some basic motion profiling in here with throttling up and down.
 */
public class DriveStraightPIDWithThrottle extends CommandGroup {

	public DriveStraightPIDWithThrottle(double distance, double speed, int direction, boolean resetGyro,
			double setpoint) {
		super("DriveStraight w/ Throttle");
		requires(Robot.driveTrain);

		double firstThrottleDistance = 0;
		double secondThrottleDistance = 0;
		double firstThrottleSpeed = .6;
		double secondThrottleSpeed = .45;
		double totalThrottleDistance = 0;

		if (speed >= RobotMap.AISLE_SPEED) {
			if (speed >= RobotMap.FRONT_SCALE_FULL_SPEED) {
				firstThrottleDistance = 90;
				secondThrottleDistance = 10;
			} else {
				firstThrottleDistance = 60;
				secondThrottleDistance = 25;
			}
			totalThrottleDistance = firstThrottleDistance + secondThrottleDistance;
			addSequential(
					new DriveStraightPID(distance - totalThrottleDistance, speed, direction, resetGyro, setpoint));
			addSequential(new DriveStraightPID(firstThrottleDistance, firstThrottleSpeed, direction, false, setpoint));
			addSequential(
					new DriveStraightPID(secondThrottleDistance, secondThrottleSpeed, direction, false, setpoint));
		} else {
			addSequential(new DriveStraightPID(distance, speed, direction, resetGyro, setpoint));
		}

	}
}
