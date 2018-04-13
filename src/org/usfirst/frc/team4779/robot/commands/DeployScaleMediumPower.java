package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeLowEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeMediumEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeployScaleMediumPower extends CommandGroup {

    public DeployScaleMediumPower() {
       	addSequential(new SetLiftSetPointPID(RobotMap.scaleHeight));
    	addSequential(new VacCubeMediumEject());
//    	addSequential(new VacCubeLowEject());
    	//addSequential(new TimerCommand(0.5));
    	addParallel(new DriveStraightPID(12, RobotMap.THROTTLE_SPEED, RobotMap.REVERSE, false), 1);
    	//addParallel( new VacCubeOff(),);
    	addParallel(new SetLiftSetPointPID(RobotMap.portalHeight),2);
    }
}
