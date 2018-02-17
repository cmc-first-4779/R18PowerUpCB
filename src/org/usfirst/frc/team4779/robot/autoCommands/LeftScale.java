package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeployScale;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScale extends CommandGroup {

    public LeftScale() {
    	System.out.println("This is my scale side " + Robot.myScaleSide);
    	if (Robot.myScaleSide == 'L') {

    	addParallel(new SetLiftSetPointPID(50));
		addSequential(new DriveStraightPID(215, 0.9, RobotMap.FORWARD));
		addSequential(new DriveStraightPID(70, 0.6, RobotMap.FORWARD));
    		addSequential(new TimerCommand(1)); 
    		addParallel(new DriveTurnPID(90));
    		//addSequential(new TimerCommand(1));
    		addSequential(new SetLiftSetPointPID(70));
    		addParallel(new DriveStraightPID (5, 0.75, RobotMap.FORWARD));
    		addParallel(new DeployScale()); 
    	}else {
    		addSequential(new DriveStraightPID(25, 0.75, RobotMap.FORWARD));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveTurnPID(90));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveStraightPID(13, 0.75, RobotMap.FORWARD));
    		addSequential(new DriveTurnPID(90));
    		addSequential(new DriveStraightPID(4, 0.75, RobotMap.FORWARD));
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

