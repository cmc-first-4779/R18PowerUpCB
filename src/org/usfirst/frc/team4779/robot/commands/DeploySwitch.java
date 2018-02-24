package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeploySwitch extends CommandGroup {

    public DeploySwitch() {
    	addSequential(new SetLiftSetPointPID(RobotMap.switchHeight));
    	addSequential(new VacCubeEject());
    	addSequential(new TimerCommand(1));
    	addParallel( new VacCubeOff());
    	addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight));
    	
     
    }
}
