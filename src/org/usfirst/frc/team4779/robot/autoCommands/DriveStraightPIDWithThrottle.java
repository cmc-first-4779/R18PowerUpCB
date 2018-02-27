package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightPIDWithThrottle extends CommandGroup {

	public DriveStraightPIDWithThrottle(double distance, double speed, int direction, boolean resetGyro,
			double setpoint) {
		super("DriveStraight w/ Throttle");
		requires(Robot.driveTrain);

		double firstThrottleDistance = 0;
		double secondThrottleDistance = 0;
		double firstThrottleSpeed = .6;
		double secondThrottleSpeed = .4;
		double totalThrottleDistance = 0;

		if (speed >= .9) {
			firstThrottleDistance = 80;
			secondThrottleDistance = 20;
		} else if (speed >= .8) {
			firstThrottleDistance = 30;
			secondThrottleDistance = 10;
		} else {
			firstThrottleDistance = 0;
			secondThrottleDistance = 10;
		}

		totalThrottleDistance = firstThrottleDistance + secondThrottleDistance;
		addSequential(new DriveStraightPID(distance - totalThrottleDistance, speed, direction, resetGyro, setpoint));
		addSequential(new DriveStraightPID(firstThrottleDistance, firstThrottleSpeed, direction, false, setpoint));
		addSequential(new DriveStraightPID(secondThrottleDistance, secondThrottleSpeed, direction, false, setpoint));
	}
}
