package org.usfirst.frc.team4779.robot.autoCommands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DeploySwitch;
import org.usfirst.frc.team4779.robot.commands.TimerCommand;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveTurnPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitch extends CommandGroup {

    public RightSwitch() {
    	System.out.println("This is my switch side " + Robot.mySwitchSide);
	if (Robot.mySwitchSide == 'L') {
		
		addSequential(new DriveStraightPID(27, 0.75, RobotMap.FORWARD));
		addSequential(new TimerCommand(2));
		addSequential(new DriveTurnPID(-90));
		addSequential(new TimerCommand(2));
		addSequential(new DriveStraightPID(15, 0.75, RobotMap.FORWARD));
		addSequential(new TimerCommand(2));
		addSequential(new DriveTurnPID(-90));
		addSequential(new TimerCommand(2));
		addSequential(new DriveStraightPID(6, 0.75, RobotMap.FORWARD));
		addSequential(new DeploySwitch());
	}else {
		addSequential(new DriveStraightPID(18, 0.75, RobotMap.FORWARD));
		addSequential(new TimerCommand(2));
		addSequential(new DriveTurnPID(-90));
		addSequential(new TimerCommand(2));
		addSequential(new DriveStraightPID(3, 0.75, RobotMap.FORWARD));
		addSequential(new DeploySwitch());
		
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
