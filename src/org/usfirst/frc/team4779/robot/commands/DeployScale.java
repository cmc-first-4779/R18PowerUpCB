package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeployScale extends CommandGroup {

    public DeployScale() {
       	addSequential(new SetLiftSetPointPID(RobotMap.scaleHeight));
    	addSequential(new VacCubeEject());
    	addSequential(new TimerCommand(0.5));
    	addSequential(new DriveStraightPID(12, RobotMap.THROTTLE_SPEED, RobotMap.REVERSE));
    	addParallel( new VacCubeOff());
    	addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight));
    }
}
