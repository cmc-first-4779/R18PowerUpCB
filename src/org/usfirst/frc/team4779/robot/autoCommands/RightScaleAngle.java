package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeployScale;
import org.usfirst.frc.team4779.robot.commands.DeployScaleMediumPower;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *   This command is used when starting from the right starting position and going to the scale from an angle.
 *   We used later in competitions for Marysville and States.
 *   
 *   The if/else statements take the input from the Field Management System (FMS) and then determine
 *   side of the scale or switch the robot must go to.
 */
public class RightScaleAngle extends CommandGroup {

	public RightScaleAngle() {
		super("Right Scale");
		if (Robot.myScaleSide == 'R') {
			addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
			addSequential(new DriveStraightPIDWithThrottle(RobotMap.SCALE_ANGLE_DISTANCE,
					RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD, true, RobotMap.NORTH + 3));
			addSequential(new TimerCommand(.25));
			addParallel(new DriveStraightPIDWithThrottle(RobotMap.FRONT_SCALE_ANGLE_APPROACH_DISTANCE,
					RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH - 60));
			 addSequential(new DeployScale());
			 addSequential(new DriveTurnPID(-RobotMap.SOUTH + 30, false));
			 addParallel(new DriveStraightPIDWithThrottle(80, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, -RobotMap.SOUTH + 30));
			 addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight));
			 addSequential(new VacCubeIntake(), 6);
		} else {
		    addParallel(new SetLiftSetPointPID(RobotMap.portalHeight));
			addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_DISTANCE, RobotMap.FRONT_SCALE_FULL_SPEED,
					RobotMap.FORWARD, true, RobotMap.NORTH));
			addSequential(new TimerCommand(0.25));
		    addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
			addSequential(new DriveTurnPID(RobotMap.WEST, false));
			addSequential(new TimerCommand(0.25));
			addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_LENGTH_TO_SCALE, RobotMap.AISLE_SPEED,
					RobotMap.FORWARD, false, RobotMap.WEST));
			addSequential(new TimerCommand(0.25));
			addSequential(new DriveTurnPID(RobotMap.NORTH, false));
			addSequential(new TimerCommand(0.25));
			addParallel(new DriveStraightPIDWithThrottle(RobotMap.AISLE_SCALE_APPROACH_DISTANCE,
					RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH));
			//addSequential(new DeployScale());
			addSequential(new DeployScale());
		}

	}

}
