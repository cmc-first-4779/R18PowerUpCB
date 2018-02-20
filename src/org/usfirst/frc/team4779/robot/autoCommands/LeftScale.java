package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeployScale;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftScale extends CommandGroup {

    public LeftScale() {
    	super("Left Scale");
    	if (Robot.myScaleSide == 'L') {

//    		addParallel(new SetLiftSetPointPID(50));
//    		addSequential(new DriveStraightPID(215, 0.9, RobotMap.FORWARD));
//    		addSequential(new DriveStraightPID(70, 0.6, RobotMap.FORWARD));
//    		addSequential(new TimerCommand(1)); 
//    		addParallel(new DriveTurnPID(RobotMap.RIGHT));
//    		//addSequential(new TimerCommand(1));
//    		addSequential(new SetLiftSetPointPID(70));
//    		addParallel(new DriveStraightPID (5, 0.75, RobotMap.FORWARD));
//    		addSequential(new DeployScale()); 
    		
    		addSequential(new DriveStraightPID(24, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		addSequential(new DriveTurnPID(-45));
    		addSequential(new TimerCommand(1));
    		addSequential(new DriveStraightPID(24, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD)); 
    		addSequential(new TimerCommand(1));
    		addSequential(new DriveTurnPID(45));
    		addSequential(new TimerCommand(1));
    		addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
    		addSequential(new DriveStraightPID((RobotMap.FRONT_SCALE_DISTANCE - RobotMap.SCALE_THROTTLE_DOWN_DISTANCE), RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD));
    		addSequential(new DriveStraightPID(RobotMap.SCALE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1)); 
    		addParallel(new DriveTurnPID(RobotMap.RIGHT));
    		//addSequential(new TimerCommand(1));
    		addSequential(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_LOW_SPEED));
    		addParallel(new DriveStraightPID (RobotMap.FRONT_SCALE_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new DeployScale()); 
    	}
    	else {
    		SmartDashboard.putString("Auto Current command", "Drive Straight to aisle.");
    		addSequential(new DriveStraightPID((RobotMap.AISLE_DISTANCE - RobotMap.AISLE_THROTTLE_DOWN_DISTANCE), RobotMap.FRONT_SCALE_FULL_SPEED, RobotMap.FORWARD));
    		addSequential(new DriveStraightPID(RobotMap.AISLE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
     		addSequential(new TimerCommand(1));
    		SmartDashboard.putString("Auto Current command", "Lift cube up a bit and turn right.");
     		addParallel(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_HIGH_SPEED));
     		addSequential(new DriveTurnPID(RobotMap.RIGHT));
     		addSequential(new TimerCommand(.5));     		
    		SmartDashboard.putString("Auto Current command", "Drive straight to other side.");
    		addSequential(new DriveStraightPID((RobotMap.AISLE_LENGTH_TO_SCALE - RobotMap.AISLE_THROTTLE_DOWN_DISTANCE), RobotMap.AISLE_SPEED, RobotMap.FORWARD));
     		addSequential(new DriveStraightPID(RobotMap.AISLE_THROTTLE_DOWN_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1));
    		SmartDashboard.putString("Auto Current command", "Turn towards scale and lift higher");
    		addSequential(new DriveTurnPID(RobotMap.LEFT));
    		//addSequential(new SetLiftSetPointPID(RobotMap.LIFT_SETPOINT_LOW_SPEED));
    		SmartDashboard.putString("Auto Current command", "Drive rest of way to scale");
    		addSequential(new DriveStraightPID(RobotMap.AISLE_SCALE_APPROACH_DISTANCE, RobotMap.THROTTLE_SPEED, RobotMap.FORWARD));
    		SmartDashboard.putString("Auto Current command", "Deploy to scale");
    		addSequential(new DeployScale()); 
    	}
    		
    	}
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

