package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *   THIS COMMAND SHOULD PROBABLY BE DELETED AS WE WILL NEVER APPROACH THE SCALE FROM THE MIDDLE 
 *   STARTING POSITION
 */
public class MiddleScale extends CommandGroup {

    public MiddleScale() {
    	super("Middle Scale");
    	addSequential(new DriveStraightPID(3,.75, 1 ));
    	addSequential(new TimerCommand(5));
    	addSequential (new DriveTurnPID(-90));
    	addSequential(new TimerCommand(5));
    	addSequential (new DriveStraightPID(3, .75, -1));
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
