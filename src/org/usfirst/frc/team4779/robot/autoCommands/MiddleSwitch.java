package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
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
    		System.out.println("leftswitchauto");
    		System.out.println(Robot.mySwitchSide);
    		addSequential(new DriveStraightPID(5, 0.75, 1));
    		addSequential(new DriveTurnPID(90));
    		addSequential(new DriveStraightPID(2, 0.75, 1));
    		addSequential(new DriveTurnPID(-70));
    			} 
    	else {
    		System.out.println("rightswitchauto");
    		System.out.println(Robot.mySwitchSide);
    		/*addSequential(new DriveStraightPID(3, 0.75, 1));
    		addSequential(new DriveTurnPID(-90));
    		addSequential(new DriveStraightPID(4, 0.75, 1));
    		addSequential(new DriveTurnPID(50));
    		//execute commands to go to right switch*/
    		addSequential(new DriveStraightPID(3, .75, 1));
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
