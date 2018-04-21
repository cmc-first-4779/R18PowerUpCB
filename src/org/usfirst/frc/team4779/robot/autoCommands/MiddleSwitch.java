package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.PickUpCubeFromLeftSwitch;
import org.usfirst.frc.team4779.robot.commands.PickUpCubeFromRightSwitch;
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
					new DriveStraightPIDWithThrottle(RobotMap.SWITCH_AISLE_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, true, RobotMap.NORTH));
			addSequential(new TimerCommand(0.25));
			addSequential(new DriveTurnPID(RobotMap.WEST, false));
			addSequential(new DriveStraightPID(RobotMap.MIDDLE_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
			addSequential(new TimerCommand(0.25));
			addSequential(new DriveTurnPID(RobotMap.NORTH, false));
			addSequential(new DriveStraightPID(RobotMap.SWITCH_AISLE_APPROACH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED,
					RobotMap.FORWARD, false, RobotMap.NORTH), 3);
			addSequential(new DeploySwitch());
			//Lower the lift and try to pickup another cube
		//	addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight), 2);
			addSequential(new PickUpCubeFromLeftSwitch());
			//Deploy the second cube
			addSequential(new DeploySwitch());
			//Lower the lift and try to pickup another cube
		//	addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight), 2);
 			addSequential(new PickUpCubeFromLeftSwitch());
			//Deploy the 3rd cube.
			addSequential(new DeploySwitch());			

		} else {
			// execute commands to go to the right switch
			// start by setting the lift height for the switch and driving straight towards the switch
			addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
			addSequential(new DriveStraightPIDWithThrottle(RobotMap.SIDE_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED,
					RobotMap.FORWARD, true, RobotMap.NORTH), 4);
			addSequential(new TimerCommand(0.25));
			//Deploy the cube into the switch
			addSequential(new DeploySwitch());
			//Lower the lift and try to pickup another cube
			//addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight), 2);
			addSequential(new PickUpCubeFromRightSwitch());
			//Deploy the second cube
			//addSequential(new DeploySwitch());
			//Lower the lift and try to pickup another cube
			//addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight), 2);
 			addSequential(new PickUpCubeFromRightSwitch());
			//Deploy the 3rd cube.
			//addSequential(new DeploySwitch());
		}

	}
}
