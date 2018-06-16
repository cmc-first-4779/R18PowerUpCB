package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
//import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveStraightPID;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeLowEject;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *  This command deploys a cube to the switch during Auton.
 */
public class DeploySwitch extends CommandGroup {

    public DeploySwitch() {
    	addSequential(new SetLiftSetPointPID(RobotMap.switchHeight));
    	addSequential(new VacCubeEject(),1);
//    	addParallel(new SetLiftSetPointPID(RobotMap.pickUpHeight),2);
    }
}
