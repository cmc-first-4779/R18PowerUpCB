package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.autoCommands.DriveStraightPIDWithThrottle;
import org.usfirst.frc.team4779.robot.commands.lift.SetLiftSetPointPID;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeIntake;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeIntakeWithSensor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpCubeFromLeftSwitch extends CommandGroup {
	double currentPickupAngle = RobotMap.LEFT_SIDE_CUBE_PILE_APPROACH_ANGLE;
    public PickUpCubeFromLeftSwitch() {
    	//Back up from switch
    	addSequential(new DriveStraightPIDWithThrottle(RobotMap.SWITCH_BACKUP_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.REVERSE, false, RobotMap.NORTH));
    	//Drive towards the pile at an angle and turn on vacube
    	addParallel(new DriveStraightPIDWithThrottle(RobotMap.CUBE_PILE_APPROACH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, RobotMap.LEFT_SIDE_CUBE_PILE_APPROACH_ANGLE));
    	
    	addSequential(new VacCubeIntake(),3) ;//want to be able to remove this hard timeout. But will require proximity sensor working.  
    	while(!Robot.vacCube.hasCube()) {
    		addSequential(new DriveStraightPIDWithThrottle(RobotMap.PICKUP_ADJUST_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.REVERSE, false, currentPickupAngle));
    		currentPickupAngle = currentPickupAngle + 3;
    		addParallel(new DriveStraightPIDWithThrottle(RobotMap.PICKUP_ADJUST_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, currentPickupAngle));
    		addSequential(new VacCubeIntake(),1.5);
    	}
    	//Backup after grabbing cube then Drive Forward
    	addSequential(new DriveStraightPIDWithThrottle(RobotMap.CUBE_PILE_APPROACH_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.REVERSE, false, calculateNewAngle()));
    	addParallel(new DriveStraightPIDWithThrottle(RobotMap.SWITCH_BACKUP_DISTANCE, RobotMap.FRONT_SWITCH_SPEED, RobotMap.FORWARD, false, RobotMap.NORTH));
    	addSequential(new SetLiftSetPointPID(RobotMap.switchHeight));
    }
    
    private double calculateNewAngle() {
    	double extraAngleToAdd = (currentPickupAngle - RobotMap.LEFT_SIDE_CUBE_PILE_APPROACH_ANGLE)/2;
    	return RobotMap.LEFT_SIDE_CUBE_PILE_APPROACH_ANGLE + extraAngleToAdd;
    }
}
