package org.usfirst.frc.team4779.robot.limelightcommandgroups;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LimelightSeek extends CommandGroup {

	
    public LimelightSeek() {
    	super("Limelight Seek");
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
    	
    	//Set the local targetValid variable to the 'tv' value from the Limelight Network Table.
    	double targetValid = Robot.limelight.limelightGetTV();
    	System.out.println("targetValid Value:  " + targetValid);
    	//While the Limelight doesn't see a target...   turn in a circle searching..
    	System.out.println("Searching for the target...");
    	while(targetValid == 0) {
    		addSequential(new DriveTurnPID(RobotMap.LIMELIGHT_SEEK_DEG_TURN, false));  //Turn a small number of degrees
    		System.out.println("Turning degrees: " + RobotMap.LIMELIGHT_SEEK_DEG_TURN);
    		targetValid = Robot.limelight.limelightGetTV();  //Do we see it now?
        	System.out.println("targetValid Value:  " + targetValid);
    		System.out.println("Still don't see it..  Looping..");
    	}
    	
    	//Now that the Light has seen its target, let's point the robot at the target.
    	System.out.println("targetValid Value:  " + targetValid);
    	System.out.println("See it now..");
    	double tx = Robot.limelight.limelightGetTX();
    	System.out.println("Aligning to target.  Degrees:  " + tx);
    	addSequential(new DriveTurnPID(tx, false));
    	 
    }
}
