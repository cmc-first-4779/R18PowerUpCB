package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitch extends CommandGroup {
	
    public MiddleSwitch() {
    	System.out.println("This is my switch side " + Robot.mySwitchSide);
    	if (Robot.mySwitchSide == 'L') {
    		//execute commands to go to left switch
    		addSequential(new DriveStraightPID(24, 0.75, 1));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveTurnPID(-90));
    		addSequential(new TimerCommand(2));
    		addSequential(new DriveStraightPID(2, 0.75, 1));
    		addSequential(new DriveTurnPID(90));
    		addSequential (new DriveStraightPID(8, .75, 1));
    		addSequential (new DeploySwitch());
    			} 
    	else {
    		//execute commands to go to the right switch
    		addSequential(new DriveStraightPID(36, .75, 1));
    		addSequential(new DeploySwitch());
    	}
    	
       
    }
}
