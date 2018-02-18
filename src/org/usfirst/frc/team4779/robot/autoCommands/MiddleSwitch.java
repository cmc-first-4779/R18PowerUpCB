package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitch extends CommandGroup {
	
    public MiddleSwitch() {
    	System.out.println("This is my switch side " + Robot.mySwitchSide);
    	if (Robot.mySwitchSide == 'L') {
    		//execute commands to go to left switch
    		addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
    		addSequential(new DriveStraightPID(RobotMap.SIDE_SWITCH_HALF_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		addSequential(new DriveTurnPID(RobotMap.LEFT));
    		addSequential(new DriveStraightPID(72, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		addSequential(new DriveTurnPID(RobotMap.RIGHT));
    		addSequential(new DriveStraightPID(RobotMap.SIDE_SWITCH_HALF_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		addSequential (new DeploySwitch());
    	} 
    	else {
    		//execute commands to go to the right switch
    		addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
    		addSequential(new DriveStraightPID(RobotMap.SIDE_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		addSequential(new DeploySwitch());
    	}
    	
       
    }
}
