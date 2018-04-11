package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.autoCommands.DriveStraightPIDWithThrottle;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeIntakeWithSensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpCubeFromRightSwitch extends CommandGroup {

    public PickUpCubeFromRightSwitch() {
    	//Back up from switch
    	addSequential(new DriveStraightPIDWithThrottle(RobotMap.SWITCH_BACKUP_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.REVERSE, false, RobotMap.NORTH));
    	//Drive towards the pile at an angle and turn on vacube
    	addParallel(new DriveStraightPIDWithThrottle(RobotMap.CUBE_PILE_APPROACH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, RobotMap.RIGHT_SIDE_CUBE_PILE_APPROACH_ANGLE));
    	addSequential(new VacCubeIntakeWithSensor());
    	//Backup after grabbing cube then Drive Forward
    	addSequential(new DriveStraightPIDWithThrottle(RobotMap.CUBE_PILE_APPROACH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.REVERSE, false, RobotMap.RIGHT_SIDE_CUBE_PILE_APPROACH_ANGLE));
    	addParallel(new DriveStraightPIDWithThrottle(RobotMap.SWITCH_BACKUP_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH));
    	addSequential(new SetLiftSetPointPID(RobotMap.switchHeight));
    }
}
