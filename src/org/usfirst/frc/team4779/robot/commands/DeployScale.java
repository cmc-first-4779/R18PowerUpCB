package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
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
    	addSequential(new TimerCommand(3));
    	addParallel( new VacCubeOff());
    	addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight));
    }
}