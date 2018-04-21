package org.usfirst.frc.team4779.robot.commands.vaccube;

import org.usfirst.frc.team4779.robot.Robot;

//  WAS GOING TO BE USED TO TAKE IN A CUBE USING THE PROXIMITY SENSOR.  
//   NOTE:  NOT USED IN COMPETITION.

import edu.wpi.first.wpilibj.command.Command;

public class VacCubeIntakeWithSensor extends Command {
    public VacCubeIntakeWithSensor() {
    	requires(Robot.vacCube);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vacCube.vacCubeIntake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.vacCube.vacCubeIntake();
    }
	@Override
	protected boolean isFinished() {
		return(Robot.vacCube.hasCube());
	}

}
