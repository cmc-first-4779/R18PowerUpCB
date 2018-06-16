package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
//import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeLowEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *    This command deploys a cube to the scale in Auton.    It also backs the robot up a foot and then lowers
 *    the lift.
 */
public class DeployScale extends CommandGroup {

    public DeployScale() {
       	addSequential(new SetLiftSetPointPID(RobotMap.scaleHeight));
    	addSequential(new VacCubeEject());
//    	addSequential(new VacCubeLowEject());
    	//addSequential(new TimerCommand(0.5));
//    	addParallel(new DriveStraightPID(12, RobotMap.THROTTLE_SPEED, RobotMap.REVERSE, false), 1);
    	//addParallel( new VacCubeOff(),);
    	addParallel(new SetLiftSetPointPID(RobotMap.portalHeight),2);
    }
}
