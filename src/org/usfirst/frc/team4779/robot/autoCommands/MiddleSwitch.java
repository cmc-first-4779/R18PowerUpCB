package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitch extends CommandGroup {

	public MiddleSwitch() {
		super("Middle Switch");
		if (Robot.mySwitchSide == 'L') {
			// execute commands to go to left switch
			addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
			addSequential(
					new DriveStraightPIDWithThrottle(RobotMap.SWITCH_AISLE_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, true, RobotMap.NORTH));
			addSequential(new TimerCommand(0.25));
			addSequential(new DriveTurnPID(RobotMap.WEST, false));
			addSequential(new DriveStraightPID(RobotMap.MIDDLE_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
			addSequential(new TimerCommand(0.25));
			addSequential(new DriveTurnPID(RobotMap.NORTH, false));
			addSequential(new DriveStraightPID(RobotMap.SWITCH_AISLE_APPROACH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED,
					RobotMap.FORWARD, false, RobotMap.NORTH), 5);
			addSequential(new TimerCommand(0.25));
			addSequential(new DeploySwitch());
			addSequential(new DriveTurnPID(RobotMap.EAST, false));
			addParallel(new DriveStraightPID(48, .7, RobotMap.FORWARD, false, RobotMap.EAST));
			addSequential(new VacCubeIntake(), 5);
		} else {
			// execute commands to go to the right switch
			addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
			addSequential(new DriveStraightPIDWithThrottle(RobotMap.SIDE_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED,
					RobotMap.FORWARD, true, RobotMap.NORTH), 4);
			addSequential(new TimerCommand(0.25));
			addSequential(new DeploySwitch());
			addSequential(new DriveTurnPID(RobotMap.WEST, false));
			addParallel(new DriveStraightPID(48, .7, RobotMap.FORWARD, false, RobotMap.WEST));
			addSequential(new VacCubeIntake(), 5);
		}

	}
}
