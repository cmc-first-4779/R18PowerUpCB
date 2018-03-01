package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeployScale;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveAnglePID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	super("Right Scale");
	if (Robot.myScaleSide == 'L') {
		
		//addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
		addSequential(new DriveStraightPID((RobotMap.AISLE_DISTANCE - RobotMap.AISLE_SCALE_APPROACH_DISTANCE), RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD));
		addSequential(new DriveStraightPID(RobotMap.AISLE_SCALE_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false));
 		addSequential(new TimerCommand(0.5));
 		//addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
 		addSequential(new DriveTurnPID(RobotMap.WEST, false));
 		addSequential(new TimerCommand(0.5));     		
		addSequential(new DriveStraightPID((RobotMap.AISLE_LENGTH_TO_SCALE - RobotMap.AISLE_THROTTLE_DOWN_DISTANCE), RobotMap.AISLE_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
 		addSequential(new DriveStraightPID(RobotMap.AISLE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
		addSequential(new TimerCommand(0.5));
		addSequential(new DriveTurnPID(RobotMap.NORTH, false));
		addSequential(new TimerCommand(0.5));
		addSequential(new DriveStraightPID(RobotMap.AISLE_SCALE_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH));
		//addSequential(new DeployScale()); 
	}
	
	else  {
		//addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
		addSequential(new DriveStraightPID((RobotMap.FRONT_SCALE_DISTANCE - RobotMap.SCALE_THROTTLE_DOWN_DISTANCE), RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD, true));
		addSequential(new DriveStraightPID(RobotMap.SCALE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH));
		addSequential(new TimerCommand(.25)); 
		addSequential(new DriveTurnPID(RobotMap.WEST, false));
		addSequential(new DriveStraightPID (RobotMap.FRONT_SCALE_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD, false, RobotMap.WEST));
		//addSequential(new DeployScale());
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
}
