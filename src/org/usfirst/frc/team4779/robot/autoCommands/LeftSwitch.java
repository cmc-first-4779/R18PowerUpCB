package org.usfirst.frc.team4779.robot.autoCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

/**
 *
 */
public class LeftSwitch extends CommandGroup {
	

    public LeftSwitch() {
    	super("Left Switch");
    	if (Robot.mySwitchSide == 'L') {
    		
    		//addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
    		addSequential(new DriveStraightPID(RobotMap.FRONT_SWITCH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		addSequential(new DriveTurnPID(RobotMap.EAST, false));
    		addSequential(new DriveStraightPID(RobotMap.FRONT_SWITCH_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.EAST));
    		//addSequential (new DeploySwitch());
    		}
    	else {
    		//addParallel(new SetLiftSetPointPID(RobotMap.switchHeight));
    		addSequential(new DriveStraightPID((RobotMap.AISLE_DISTANCE - RobotMap.AISLE_SCALE_APPROACH_DISTANCE), RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD));
    		addSequential(new DriveStraightPID(RobotMap.AISLE_SCALE_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false));
     		addSequential(new TimerCommand(0.5));
         	addSequential(new DriveTurnPID(RobotMap.EAST, false));
         	addSequential(new TimerCommand(0.5));
         	addSequential(new DriveStraightPID((RobotMap.AISLE_LENGTH_TO_SWITCH - RobotMap.AISLE_THROTTLE_DOWN_DISTANCE), RobotMap.AISLE_SPEED, RobotMap.FORWARD, false, RobotMap.EAST));
         	addSequential(new DriveStraightPID(RobotMap.AISLE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.EAST));
         	addSequential(new TimerCommand(0.5));
        	//addSequential(new DriveTurnPID(RobotMap.SOUTH, false));
        //	addSequential(new TimerCommand(0.5));
        	//addSequential(new DriveStraightPID(RobotMap.AISLE_SWITCH_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.SOUTH));
        	//addSequential (new DeploySwitch());
    		}
    	
    		
    		}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }

