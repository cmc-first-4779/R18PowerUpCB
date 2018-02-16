package org.usfirst.frc.team4779.robot.autoCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;

/**
 *
 */
public class LeftSwitch extends CommandGroup {
	

    public LeftSwitch() {
    	System.out.println("This is my switch side " + Robot.mySwitchSide);
    	if (Robot.mySwitchSide == 'L') {
    		//execute commands to go to left switch
    		addSequential(new DriveStraightPID(24, 0.75, 1));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveTurnPID(90));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveStraightPID(2, 0.75, 1));
    		addSequential (new DeploySwitch());
    		}
    		else {
    		addSequential(new DriveStraightPID(30, 0.75, 1));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveTurnPID(90));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveStraightPID(12, 0.75, 1));
    		addSequential(new DeploySwitch());
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

