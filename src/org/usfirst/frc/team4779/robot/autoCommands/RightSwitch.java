package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *   *   This command is designed to hit the switch from the right starting position.
 *   
 *   NOTE:   IF THE FMS tells us to hit the right side scale in the "if" statement, this routine will pull
 *   a foul penalty based on current rules as a powercube may block our ability to touch the switch before
 *   deploying.
 */
public class RightSwitch extends CommandGroup {

    public RightSwitch() {
    	super("Right Switch");
	if (Robot.mySwitchSide == 'L') {
		addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_DISTANCE, RobotMap.FRONT_SCALE_FULL_SPEED, 
				RobotMap.FORWARD, true, RobotMap.NORTH));
 		addSequential(new TimerCommand(0.25));
     	addSequential(new DriveTurnPID(RobotMap.WEST, false));
     	addSequential(new TimerCommand(0.25));
     	addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_LENGTH_TO_SWITCH, RobotMap.AISLE_SPEED, 
     			RobotMap.FORWARD, false, RobotMap.WEST));
     	addSequential(new TimerCommand(0.25));
    	addSequential(new DriveTurnPID(-RobotMap.SOUTH, false));
    	addSequential(new TimerCommand(0.25));
    	addSequential(new DriveStraightPIDWithThrottle(RobotMap.AISLE_SWITCH_APPROACH_DISTANCE, 
    			RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, -RobotMap.SOUTH), 2);
    	addSequential (new DeploySwitch());
	}
	else {
		addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.FRONT_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, 
				RobotMap.FORWARD, true, RobotMap.NORTH));
		addSequential(new TimerCommand(0.25));
		addSequential(new DriveTurnPID(RobotMap.WEST, false));
		addSequential(new DriveStraightPIDWithThrottle(RobotMap.FRONT_SWITCH_APPROACH_DISTANCE, 
				RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
		addSequential (new DeploySwitch());
		
	}

    }
}
