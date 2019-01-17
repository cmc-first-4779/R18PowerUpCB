package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveSeekLL;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveLL extends CommandGroup {

    public DriveLL() {
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
    	System.out.println("Starting DriveSeekLL.");
    	addSequential(new DriveSeekLL());
    	System.out.println("Ending DriveSeekLL.");
    	System.out.println("Starting DriveTurnPID.");
     	addSequential(new DriveTurnPID(Robot.limelight.getTX(), true));
     	System.out.println("Ending DriveTurnPID.");
    	addSequential(new DriveStraightPID(Robot.limelight.getDistance(), RobotMap.LIMELIGHT_SEEK_DRIVE_POWER, RobotMap.FORWARD));
    	
    }
}
